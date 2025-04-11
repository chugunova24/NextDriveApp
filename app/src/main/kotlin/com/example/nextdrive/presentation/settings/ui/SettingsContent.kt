package com.example.nextdrive.presentation.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.nextdrive.presentation.settings.ui.ProfileSection
import com.example.nextdrive.presentation.settings.SettingsViewModel


@Composable
fun SettingsContent(
    viewModel: SettingsViewModel,
    navController: NavController,
    onProfileClick: () -> Unit
) {
    Column {
        Text(
            text = "Настройки",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        ProfileSection(viewModel = viewModel, onProfileClick = onProfileClick)
        SettingsList(viewModel, LocalContext.current)
    }
}


//@Composable
//fun SettingsContent(
//    viewModel: SettingsViewModel,
//    navController: NavController,
//    onProfileClick: () -> Unit
//) {
//    Column {
//        // Заголовок
//        Text(
//            text = "Настройки",
//            style = MaterialTheme.typography.headlineMedium,
//            modifier = Modifier.padding(16.dp)
//        )
//
//        // Профиль пользователя
//        ProfileSection(viewModel = viewModel, onProfileClick = onProfileClick)
//
//        // Настройки
//        SettingsList(viewModel, LocalContext.current)
//    }
//}