package com.example.nextdrive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.example.nextdrive.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.nextdrive.data.supabase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController)

        }
    }
}