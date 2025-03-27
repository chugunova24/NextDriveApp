package com.example.nextdrive.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.nextdrive.presentation.screens.MainScreen
import com.example.nextdrive.presentation.screens.SplashScreen
import com.example.nextdrive.presentation.screens.isOnboardingCompleted
import com.example.nextdrive.presentation.screens.saveOnboardingCompleted
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.nextdrive.presentation.screens.LoginOrSignUpScreen
import com.example.nextdrive.presentation.screens.LoginScreen
import com.example.nextdrive.presentation.screens.OnboardingScreen
import com.example.nextdrive.presentation.screens.SignUpScreen
import com.example.nextdrive.presentation.screens.SignUpStep2Screen
import com.example.nextdrive.presentation.screens.SignUpStep3Screen
import com.example.nextdrive.presentation.screens.SuccessSignUpScreen


fun isUserAuthenticated(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("is_logged_in", false)
}


@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current

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
            SignUpScreen(navController) // Точка входа в экран регистрации
        }
        composable("signup_step2_screen") {
            SignUpStep2Screen(navController)
        }
        composable("signup_step3_screen") {
            SignUpStep3Screen(navController)
        }
        composable("success_signup_screen") {
            SuccessSignUpScreen(navController)
        }
        composable("main_screen") {
            MainScreen()
        }
    }
}

