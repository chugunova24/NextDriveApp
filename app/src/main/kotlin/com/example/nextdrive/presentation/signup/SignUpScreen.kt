package com.example.nextdrive.presentation.signup

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel

import com.example.nextdrive.presentation.signup.ui.TopBar
import com.example.nextdrive.presentation.signup.data.SignUpState
import com.example.nextdrive.presentation.signup.data.SignUpStep
import com.example.nextdrive.presentation.signup.ui.Step1Content
import com.example.nextdrive.presentation.signup.ui.Step2Content
import com.example.nextdrive.presentation.signup.ui.Step3Content
import com.example.nextdrive.presentation.signup.ui.SuccessContent


@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel: SignUpViewModel = koinViewModel()
    val formData by viewModel.formData // Автоматическое отслеживание изменений
    val currentStep by viewModel.currentStep

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = when (currentStep) {
                    SignUpStep.STEP1 -> "Шаг 1/3"
                    SignUpStep.STEP2 -> "Шаг 2/3"
                    SignUpStep.STEP3 -> "Шаг 3/3"
                    SignUpStep.SUCCESS -> "Регистрация"
                },
                showBack = currentStep != SignUpStep.STEP1
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp)
        ) {
            when (currentStep) {
                SignUpStep.STEP1 -> Step1Content(formData, viewModel)
                SignUpStep.STEP2 -> Step2Content(formData, viewModel)
                SignUpStep.STEP3 -> Step3Content(formData, viewModel)
                SignUpStep.SUCCESS -> SuccessContent(navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen(
        rememberNavController()
    )
}