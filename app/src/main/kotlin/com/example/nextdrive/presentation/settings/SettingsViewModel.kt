package com.example.nextdrive.presentation.settings

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextdrive.domain.auth.AuthUseCase
import com.example.nextdrive.data.UserProfile
import com.example.nextdrive.domain.storage.StorageUseCase
import com.example.nextdrive.domain.user.UserUseCase
import kotlinx.coroutines.launch
import java.nio.file.Files.size


class SettingsViewModel(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase,
    private val storageUseCase: StorageUseCase,
//    private val settingsUseCase: SettingsUseCase
) : ViewModel() {

    var avatarBytes by mutableStateOf<ByteArray?>(null)
        private set

    var avatarLoading by mutableStateOf(false)
        private set

    var avatarError by mutableStateOf<String?>(null)
        private set

    // Данные пользователя
    var userProfile by mutableStateOf(UserProfile())
        private set

    var userEmail by mutableStateOf("")
        private set

    // Настройки
    var isDarkTheme by mutableStateOf(false)
        private set
    var notificationsEnabled by mutableStateOf(true)
        private set

    init {
        getUserEmail()
        getUserProfile()
        getSettings()
    }


    fun getUserEmail() {
        viewModelScope.launch {
            try {
                userEmail = authUseCase.getCurrentSession()?.user?.email!!
            } catch (e: Exception){

            }
        }
    }

    fun getUserProfile() {
        var userId: String? = null

        viewModelScope.launch {
            try {
                userId = authUseCase.getCurrentSession()?.user?.id
                userId?.let {
                    userProfile = userUseCase.getUserProfile(it)
                    // Загружаем аватар при получении профиля
                    loadAvatar(userId!!)
                }
            } catch (e: Exception) {
                // Обработка ошибок
            }
        }
    }

    suspend fun loadAvatar(userId: String) {
        viewModelScope.launch {
            avatarLoading = true
            avatarError = null

            try {
                avatarBytes = storageUseCase.getUserAvatar(userId)
                if (avatarBytes == null) {
                    avatarError = "Не удалось загрузить аватар"
                }
            } catch (e: Exception) {
                avatarBytes = null
                avatarError = "Ошибка при загрузке: ${e.message ?: "Неизвестная ошибка"}"
            } finally {
                avatarLoading = false
            }
        }
    }

    fun uploadProfileImage(imageBytes: ByteArray) {
        viewModelScope.launch {
            avatarLoading = true
            avatarError = null

            val userId = authUseCase.getCurrentSession()?.user?.id
            if (userId == null) {
                avatarError = "Пользователь не авторизован"
                avatarLoading = false
                return@launch
            }

            runCatching {
                storageUseCase.uploadUserAvatar(userId, imageBytes)
            }.onSuccess {
                // После успешной загрузки обновляем аватар
                loadAvatar(userId)
            }.onFailure { e ->
                avatarError = "Ошибка загрузки аватара: ${e.message}"
            }

            avatarLoading = false
        }
    }

    fun userLogout() {
        viewModelScope.launch {
            try {
                authUseCase.logout()
            } catch (e: Exception) {
                // Обработка ошибок
            }
        }
    }

    private fun getSettings() {
        viewModelScope.launch {
            isDarkTheme = true
            notificationsEnabled = true
//            isDarkTheme = settingsUseCase.getDarkTheme()
//            notificationsEnabled = settingsUseCase.getNotificationsEnabled()
        }
    }

//    fun toggleTheme(context: Context) {
//        isDarkTheme = !isDarkTheme
//        viewModelScope.launch {
//            settingsUseCase.setDarkTheme(isDarkTheme)
//            // Применяем тему
//            if (isDarkTheme) {
////                context.setTheme(NextDriveTheme(darkTheme = true, content = ))
//            } else {
////                context.setTheme(NextDriveTheme(darkTheme = true))
//            }
//        }
//    }
    fun toggleTheme(context: Context) {
        isDarkTheme = !isDarkTheme
        viewModelScope.launch {
//            settingsUseCase.setDarkTheme(isDarkTheme)
            val themeName = if (isDarkTheme) "Темная тема" else "Светлая тема"
            Toast.makeText(
                context,
                "Тема изменена: $themeName",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun toggleNotifications(context: Context) {
        notificationsEnabled = !notificationsEnabled
        viewModelScope.launch {
//            settingsUseCase.setNotificationsEnabled(notificationsEnabled)
            Toast.makeText(
                context,
                if (notificationsEnabled) "Уведомления включены" else "Уведомления выключены",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}