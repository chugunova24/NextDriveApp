package com.example.nextdrive.presentation.screens

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextdrive.R
import androidx.compose.foundation.clickable


@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val isValidEmail = email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.isNotBlank()
    val isFormValid = isValidEmail && isPasswordValid

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Заголовки
            Text(
                text = "Войдите в аккаунт",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Пожалуйста, введите свои данные",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(128.dp))

            // Поле Email
            Text(text = "Электронная почта", modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Введите электронную почту") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = email.isNotBlank() && !isValidEmail
            )
            if (email.isNotBlank() && !isValidEmail) {
                Text(
                    text = "Некорректный email",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Поле Пароль
            Text(text = "Пароль", modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Введите пароль") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = "Показать/скрыть пароль")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = password.isNotBlank() && !isPasswordValid
            )

            Spacer(modifier = Modifier.height(8.dp))

            // TODO("Экран восстановления пароля не реализован reset_password_screen.")
            Text(
                text = "Забыли пароль?",
                color = Color(0xFF2A1246),
                modifier = Modifier
                    .padding(24.dp)
                    .clickable { navController.navigate("main_screen") }
            )

            // Кнопка "Войти"
            Button(
                onClick = {
                    val success = authenticateUser(email, password, context)
                    if (success) {
                        saveUserSession(context)
                        navController.navigate("main_screen") {
                            popUpTo("login_screen") { inclusive = true }
                        }
                    } else {
                        errorMessage = "Ошибка входа. Проверьте email и пароль."
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isFormValid,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2A1246))
            ) {
                Text(text = "Войти", color = Color.White)
            }

            // Отображение ошибки
            errorMessage?.let {
                Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    //  TODO("Не реализована аутентификация через Google.")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Войти через Google", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(64.dp))
            Text(
                modifier = Modifier.clickable { navController.navigate("signup_screen") },
                text = "Зарегистрируйтесь",
                color = Color(0xFF2A1246)
            )

        }
    }
}



// TODO("Не реализована интеграция с сервером аунтентификации.")
// Заглушка для проверки логина (здесь должен быть API-запрос)
fun authenticateUser(email: String, password: String, context: Context): Boolean {
    return if (email == "test@example.com" && password == "password") {
        true
    } else {
        Toast.makeText(context, "Неверные данные", Toast.LENGTH_SHORT).show()
        false
    }
}


// Функция для сохранения успешного входа в `SharedPreferences`
fun saveUserSession(context: Context) {
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean("is_logged_in", true).apply()
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        rememberNavController()
    )
}
