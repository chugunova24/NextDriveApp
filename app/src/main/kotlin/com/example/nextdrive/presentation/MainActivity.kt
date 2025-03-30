package com.example.nextdrive.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController

import com.example.nextdrive.navigation.NavGraph
import com.example.nextdrive.presentation.components.NextDriveTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        startKoin {
//            androidContext(this@MainActivity)
////            modules(appModule)
//        }

        setContent {
            val navController = rememberNavController()

            NextDriveTheme {
                NavGraph(
                    navController = navController
                )
            }

        }
    }
}

//import android.app.Activity
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.SideEffect
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.platform.LocalView
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
//import androidx.core.view.WindowCompat
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.navigation.NavController
//import com.example.nextdrive.navigation.AppNavGraph
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
////import my.zukoap.tasky.core.logger.Logger
//import com.example.nextdrive.presentation.theme.ProjectTheme
//
//
//class MainActivity : ComponentActivity() {
////    private val logger = Logger(this)
//    private val viewModel by viewModel<MainViewModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        val splashScreen = installSplashScreen()
//        splashScreen.setKeepOnScreenCondition {
//            viewModel.isAuthenticated.value == null
//        }
//        super.onCreate(savedInstanceState)
//        setContent {
//            val isAuthenticated = viewModel.isAuthenticated.collectAsStateWithLifecycle()
//            ProjectTheme {
//                val view = LocalView.current
//                val statusColor = ProjectTheme.colors.black
//                if (!view.isInEditMode) {
//                    SideEffect {
//                        val window = (view.context as Activity).window
////                        window.statusBarColor = statusColor.toArgb()
////                        window.navigationBarColor = statusColor.toArgb()
//                        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
//                            false
//                    }
//                }
//
//                val navHostController = rememberNavController()
//
//                isAuthenticated.value?.let {
//                    AppNavGraph(
//                        navController = navHostController,
//                        isAuthenticated = it,
//                        modifier = Modifier
//                    )
//                }
//
////                NavigationTrackingSideEffect(navHostController)
//            }
//        }
//    }
//    @Composable
//    private fun NavigationTrackingSideEffect(navHostController: NavController) {
//        DisposableEffect(navHostController) {
//            val listener =
//                NavController.OnDestinationChangedListener { controller, destination, _ ->
//                    logger.debug(
//                        "NavigationTrackingSideEffect:\n" +
//                                "\tPreviousBackStackState: ${controller.previousBackStackEntry?.destination?.route}\n" +
//                                "\tCurrent destination: ${destination.route}"
//                    )
//                }
//
//            navHostController.addOnDestinationChangedListener(listener)
//
//            onDispose {
//                navHostController.removeOnDestinationChangedListener(listener)
//            }
//        }
//    }
//}