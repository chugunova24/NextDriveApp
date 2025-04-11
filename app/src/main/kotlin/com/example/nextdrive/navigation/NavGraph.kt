package com.example.nextdrive.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nextdrive.presentation.onboarding.OnboardingScreen
import com.example.nextdrive.presentation.onboarding.isOnboardingCompleted
import com.example.nextdrive.presentation.onboarding.saveOnboardingCompleted
import com.example.nextdrive.presentation.login_or_signup.LoginOrSignUpScreen
import com.example.nextdrive.presentation.login.LoginScreen
import com.example.nextdrive.presentation.main.MainScreen
import com.example.nextdrive.presentation.settings.SettingsScreen
import com.example.nextdrive.presentation.splash.SplashScreen
import com.example.nextdrive.presentation.signup.SignUpScreen




fun isUserAuthenticated(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("is_logged_in", false)
}





@Composable
fun NavGraph(
    navController: NavHostController,
    onThemeChange: (Boolean) -> Unit
) {
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
            SignUpScreen(navController)
        }
        composable("main_screen") {
            MainScreen(navController)
        }
        composable("settings_screen") {
            SettingsScreen(navController)
        }

//        // Новые экраны для автомобилей
//        composable("car_list_screen") {
//            CarListScreen(navController)
//        }
//
//        composable("car_details_screen?carId={carId}") {
//            val carId = it.arguments?.getString("carId") ?: ""
//            CarDetailsScreen(navController, carId)
//        }
//
//        composable("car_booking_screen?carId={carId}") {
//            val carId = it.arguments?.getString("carId") ?: ""
//            CarBookingScreen(navController, carId)
//        }
//
//        composable("search_results_screen?query={query}") {
//            val query = it.arguments?.getString("query") ?: ""
//            SearchResultsScreen(navController, query)
//        }

    }
}

