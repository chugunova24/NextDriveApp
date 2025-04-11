package com.example.nextdrive.presentation.settings.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.nextdrive.presentation.settings.ui.SettingsMenuItem
import com.example.nextdrive.presentation.settings.SettingsViewModel


@Composable
fun SettingsList(
    viewModel: SettingsViewModel,
    context: Context
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        SettingsMenuItem(
            icon = Icons.Default.CalendarToday,
            title = "Мои бронирования",
            onClick = { /* TODO */ }
        )

        SettingsMenuItem(
            icon = Icons.Default.DarkMode,
            title = "Темная тема",
            trailingContent = {
                Switch(
                    checked = viewModel.isDarkTheme,
                    onCheckedChange = { viewModel.toggleTheme(context) }
                )
            }
        )

        SettingsMenuItem(
            icon = Icons.Default.Notifications,
            title = "Уведомления",
            trailingContent = {
                Switch(
                    checked = viewModel.notificationsEnabled,
                    onCheckedChange = { viewModel.toggleNotifications(context) }
                )
            }
        )

        SettingsMenuItem(
            icon = Icons.Default.DirectionsCar,
            title = "Подключить автомобиль",
            onClick = { /* TODO */ }
        )

        SettingsMenuItem(
            icon = Icons.AutoMirrored.Filled.Help,
            title = "Помощь",
            onClick = { /* TODO */ }
        )

        SettingsMenuItem(
            icon = Icons.Default.Share,
            title = "Пригласи друга",
            onClick = { /* TODO */ }
        )
    }
}