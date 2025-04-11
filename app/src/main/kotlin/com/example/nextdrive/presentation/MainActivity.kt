package com.example.nextdrive.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.nextdrive.NextDriveApp

import com.example.nextdrive.navigation.NavGraph
import com.example.nextdrive.presentation.components.NextDriveTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val app = application as NextDriveApp
            var isDarkTheme = remember { mutableStateOf(app.isDarkTheme.value) }

            NextDriveTheme(darkTheme = isDarkTheme.value) {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    onThemeChange = { dark ->
                        isDarkTheme.value = dark
                        app.isDarkTheme.value = dark
                    }
                )
            }
        }
    }
}