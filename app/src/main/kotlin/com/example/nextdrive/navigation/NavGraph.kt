package com.example.nextdrive.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nextdrive.presentation.onboarding.OnboardingScreen
import com.example.nextdrive.presentation.onboarding.isOnboardingCompleted
import com.example.nextdrive.presentation.onboarding.saveOnboardingCompleted
import com.example.nextdrive.presentation.screens.LoginOrSignUpScreen
import com.example.nextdrive.presentation.login.LoginScreen
import com.example.nextdrive.presentation.screens.MainScreen
import com.example.nextdrive.presentation.screens.SplashScreen
import com.example.nextdrive.presentation.screens.signup.SignUpScreen
import com.example.nextdrive.presentation.screens.signup.SignUpStep2Screen
import com.example.nextdrive.presentation.screens.signup.SignUpStep3Screen
import com.example.nextdrive.presentation.screens.signup.SignUpView
import com.example.nextdrive.presentation.screens.signup.SuccessSignUpScreen




fun isUserAuthenticated(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("is_logged_in", false)
}





@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current

    val signUpView: SignUpView = viewModel()

    // Проверяем, был ли онбординг пройден
    var isFirstLaunch by remember { mutableStateOf(!isOnboardingCompleted(context)) }
    var isUserLoggedIn by remember { mutableStateOf(isUserAuthenticated(context)) }

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(onTimeout = {
                when {
                    isFirstLaunch -> {
                        navController.navigate("onboarding_screen") {
                            popUpTo("splash_screen") { inclusive = true }
                        }
                    }
                    isUserLoggedIn -> {
                        navController.navigate("main_screen") {
                            popUpTo("splash_screen") { inclusive = true }
                        }
                    }
                    else -> {
                        navController.navigate("login_or_signup_screen") {
                            popUpTo("splash_screen") { inclusive = true }
                        }
                    }
                }
            })
        }
        composable("onboarding_screen") {
            OnboardingScreen(onFinish = {
                saveOnboardingCompleted(context) // Сохраняем, что онбординг пройден
                isFirstLaunch = false
                navController.navigate("login_or_signup_screen") {
                    popUpTo("onboarding_screen") { inclusive = true }
                }
            })
        }
        composable("login_or_signup_screen") {
            LoginOrSignUpScreen(navController)
        }
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("signup_screen") {
            SignUpScreen(navController, signUpView) // Точка входа в экран регистрации
        }
        composable("signup_step2_screen") {
            SignUpStep2Screen(navController, signUpView)
        }
        composable("signup_step3_screen") {
            SignUpStep3Screen(navController, signUpView)
        }
        composable("success_signup_screen") {
            SuccessSignUpScreen(navController, signUpView)
        }
        composable("main_screen") {
            MainScreen()
        }
    }
}

