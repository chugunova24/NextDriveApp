package com.example.nextdrive.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.nextdrive.navigation.NavGraph

import com.example.nextdrive.presentation.components.NextDriveTheme
import com.example.nextdrive.presentation.screens.SplashScreen


class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NextDriveTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
//                    SplashScreen(onTimeout = {
//                        navController.navigate("main_screen")
//                    })
                }
            }
        }
    }

}
