package com.example.nextdrive.presentation.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


@Composable
fun SettingsScreen(navController: NavController, settingsView: SettingsView) {
    val context = LocalContext.current
    val userProfile by settingsView.userProfile.collectAsState()
    val theme by settingsView.theme.collectAsState()
    val notificationsEnabled by settingsView.notificationsEnabled.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Профиль пользователя
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("profile_screen") }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(userProfile.avatarUrl),
                contentDescription = "User Avatar",
                modifier = Modifier.size(64.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = userProfile.name)
                Text(text = userProfile.email, color = Color.Gray)
            }
        }
        Divider()

        // Меню настроек
        SettingsMenuItem("Мои бронирования") { navController.navigate("my_bookings") }
        SettingsMenuItem("Тема") {
            settingsView.toggleTheme()
        }
        SettingsMenuItem("Уведомления") {
            settingsView.toggleNotifications()
        }
        SettingsMenuItem("Подключить свой автомобиль") { navController.navigate("add_car") }
        SettingsMenuItem("Помощь") { /* Открыть экран помощи */ }
        SettingsMenuItem("Пригласи друга") { /* Логика приглашения друга */ }
    }
}

@Composable
fun SettingsMenuItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
    }
    Divider()
}

data class UserProfile(
    val name: String,
    val email: String,
    val avatarUrl: String
)

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(
        rememberNavController(),
        SettingsView()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsMenuItem() {
    SettingsMenuItem(
        title = "ЭЭЭЭ",
        onClick = {}
    )
}
