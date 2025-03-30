package com.example.nextdrive.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.nextdrive.domain.authentication.usecase.AuthUseCase
import kotlinx.coroutines.launch
import android.content.Context
import android.util.Patterns
import android.widget.Toast


class LoginViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    // UI State
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var passwordVisible by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    val isValidEmail: Boolean
        get() = email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordValid: Boolean
        get() = password.isNotBlank()

    val isFormValid: Boolean
        get() = isValidEmail && isPasswordValid

    // Event Handlers
    fun onEmailChange(newEmail: String) {
        email = newEmail
        errorMessage = null
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        errorMessage = null
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun onLoginClick(
        context: Context,
        navController: NavController
    ) {
        if (!isFormValid) {
            errorMessage = "Пожалуйста, заполните все поля правильно"
            return
        }

        viewModelScope.launch {
            try {
                val success = authUseCase.signIn(email, password)
                if (success) {
                    saveUserSession(context)
                    navController.navigate("main_screen") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    errorMessage = "Ошибка входа. Проверьте email и пароль."
                }
            } catch (e: Exception) {
                errorMessage = "Ошибка: ${e.message}"
                Toast.makeText(context, "Ошибка входа: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onForgotPasswordClick(navController: NavController) {
        navController.navigate("reset_password_screen")
    }

    fun onSignUpClick(navController: NavController) {
        navController.navigate("signup_screen")
    }

    private fun saveUserSession(context: Context) {
        val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("is_logged_in", true).apply()
    }
}