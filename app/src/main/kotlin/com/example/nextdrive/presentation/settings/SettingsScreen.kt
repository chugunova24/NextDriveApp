package com.example.nextdrive.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import com.example.nextdrive.presentation.components.BottomNavigationBar
import com.example.nextdrive.presentation.settings.ui.ProfileDetails
import com.example.nextdrive.presentation.settings.ui.SettingsContent


@Composable
fun SettingsScreen(navController: NavController) {
    val viewModel: SettingsViewModel = koinViewModel()
    var showProfile = remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                if (showProfile.value) {
                    ProfileDetails(
                        viewModel = viewModel,
                        onBackClick = { showProfile.value = false },
                        navController = navController
                    )
                } else {
                    SettingsContent(
                        viewModel = viewModel,
                        navController = navController,
                        onProfileClick = { showProfile.value = true }
                    )
                }
            }
        }
    }
}

//@Composable
//fun SettingsScreen(navController: NavController) {
//    val viewModel: SettingsViewModel = koinViewModel()
//    var showProfile = remember { mutableStateOf(false) }
//
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController) }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            if (showProfile.value) {
//                ProfileDetails(
//                    viewModel = viewModel,
//                    onBackClick = { showProfile.value = false },
//                    navController = navController
//                )
//            } else {
//                SettingsContent(
//                    viewModel = viewModel,
//                    navController = navController,
//                    onProfileClick = { showProfile.value = true }
//                )
//            }
//        }
//    }
//}


//@Preview(showBackground = true)
//@Composable
//fun PreviewSettingsScreen() {
//    SettingsScreen(
//        rememberNavController()
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewSettingsMenuItem() {
//    SettingsMenuItem(
//        icon = Icons.Default.Home,
//        title = "ЭЭЭЭ",
////        onClick = {}
//    )
//}
