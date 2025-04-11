package com.example.nextdrive.presentation.login

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
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.nextdrive.domain.auth.AuthUseCase
//import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: LoginViewModel = koinViewModel()

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
            // Header
            Text(text = "Войдите в аккаунт", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Пожалуйста, введите свои данные", color = Color.Gray)

            Spacer(modifier = Modifier.height(128.dp))

            // Email Field
            Text(text = "Электронная почта", modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = viewModel.email,
                onValueChange = viewModel::onEmailChange,
                label = { Text("Введите электронную почту") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = viewModel.email.isNotBlank() && !viewModel.isValidEmail
            )
            if (viewModel.email.isNotBlank() && !viewModel.isValidEmail) {
                Text(
                    text = "Некорректный email",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Password Field
            Text(text = "Пароль", modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = viewModel::onPasswordChange,
                label = { Text("Введите пароль") },
                visualTransformation = if (viewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = viewModel::togglePasswordVisibility) {
                        Icon(
                            imageVector = if (viewModel.passwordVisible) Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff,
                            contentDescription = "Показать/скрыть пароль"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password
            Text(
                text = "Забыли пароль?",
                color = Color(0xFF2A1246),
                modifier = Modifier
                    .padding(24.dp)
                    .clickable { viewModel.onForgotPasswordClick(navController) }
            )

            // Login Button
            Button(
                onClick = { viewModel.onLoginClick(context, navController) },
                modifier = Modifier.fillMaxWidth(),
                enabled = viewModel.isFormValid,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2A1246))
            ) {
                Text(text = "Войти", color = Color.White)
            }

            // Error Message
            viewModel.errorMessage?.let {
                Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Google Sign-In Button
            Button(
                onClick = { /* TODO: Google auth */ },
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
                modifier = Modifier.clickable { viewModel.onSignUpClick(navController) },
                text = "Зарегистрируйтесь",
                color = Color(0xFF2A1246)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        rememberNavController()
    )
}
