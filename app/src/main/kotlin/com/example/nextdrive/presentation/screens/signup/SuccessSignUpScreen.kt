package com.example.nextdrive.presentation.screens.signup

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.nextdrive.R
import androidx.compose.runtime.LaunchedEffect
import com.example.nextdrive.domain.AuthUseCase


@Composable
fun SuccessSignUpScreen(navController: NavController, signUpView: SignUpView) {
    val authUseCase = AuthUseCase()

    val email = signUpView.getData("email") ?: ""
    val password = signUpView.getData("password") ?: ""


    LaunchedEffect(Unit) {
        try {
            authUseCase.signUp(email = email, password = password)
            navController.navigate("main_screen")
        } catch (e: Exception) {
            throw Exception("SuccessSignUpScreen: ${e.localizedMessage}")
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Пользуйтесь с удовольствием",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.check_circle_2),
                contentDescription = "Success",
                modifier = Modifier.size(128.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Поздравляем",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Вы успешно зарегистрировались. Обработка ваших персональных данных займет" +
                        " около двух часов. Спасибо за ожидание",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Кнопка "Далее"
        Button(
            onClick = {
                navController.navigate("main_screen")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2A1246), contentColor = Color.White)
        ) {
            Text(text = "Далее")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSuccessSignUpScreen() {
    SuccessSignUpScreen(
        rememberNavController(),
        SignUpView()
    )
}
