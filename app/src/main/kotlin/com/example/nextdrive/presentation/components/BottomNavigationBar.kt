package com.example.nextdrive.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import com.example.nextdrive.data.NavItem


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavItem("Главная", Icons.Default.Home, "main_screen"),
        NavItem("Избранное", Icons.Default.Favorite, "favorites"),
        NavItem("Настройки", Icons.Default.Settings, "settings_screen")
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = navController.currentDestination?.route == item.route,
                onClick = { navController.navigate(item.route) }
            )
        }
    }
}