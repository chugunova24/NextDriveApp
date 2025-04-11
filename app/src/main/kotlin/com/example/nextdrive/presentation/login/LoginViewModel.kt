package com.example.nextdrive.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.nextdrive.domain.auth.AuthUseCase
import kotlinx.coroutines.launch
import android.content.Context
import android.util.Patterns
import android.widget.Toast


data class LoginState (
    var email : String = "",
    var password : String = "",
    var passwordVisible : Boolean = false,
    var errorMessage : String? = null,
)



class LoginViewModel(
    val authUseCase: AuthUseCase
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
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
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
                if (success.isSuccess) {
                    saveUserSession(context)
                    navController.navigate("main_screen") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    errorMessage = success.toString()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка входа: ${errorMessage}", Toast.LENGTH_SHORT).show()
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