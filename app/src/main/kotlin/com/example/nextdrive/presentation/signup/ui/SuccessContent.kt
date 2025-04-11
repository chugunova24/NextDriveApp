package com.example.nextdrive.presentation.signup.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@Composable
fun SuccessContent(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            "Регистрация завершена!",
            modifier = Modifier.align(Alignment.Center)
        )
        PrimaryButton(
            text = "На главную",
            onClick = { navController.navigate("main_screen") },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}