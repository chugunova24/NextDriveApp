package com.example.nextdrive.presentation.login_or_signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextdrive.presentation.components.LogoImage
import com.example.nextdrive.presentation.components.LogoText


@Composable
fun LoginOrSignUpScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 112.dp, start = 24.dp, end = 24.dp, bottom = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        LogoText(text = "Поможем найти твою следующую поездку")
        LogoImage()

        // Кнопки внизу экрана
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = { navController.navigate("login_screen") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor  = Color(0xFF2A1246))
            ) {
                Text(text = "Войти", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("signup_screen") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.Gray),
            ) {
                Text(text = "Зарегистрироваться", color = Color.Black)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginOrSignUpScreen() {
    LoginOrSignUpScreen(
        rememberNavController()
    )
}