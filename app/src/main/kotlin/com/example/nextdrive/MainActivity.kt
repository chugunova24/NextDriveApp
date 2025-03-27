package com.example.nextdrive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nextdrive.navigation.NavGraph
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController)
        }
    }
}






//package com.example.nextdrive
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.tooling.preview.Preview
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApp()
//        }
//    }
//}

//@Composable
//fun MyApp() {
//    // Используем MaterialTheme для стилизации
//    MaterialTheme {
//        // Используем Column для вертикального размещения элементов
//        Column(
//            modifier = Modifier.fillMaxSize().padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Состояние для текста
//            var text by remember { mutableStateOf("Привет, мир!") }
//
//            // Отображаем текст
//            Text(text = text, style = MaterialTheme.typography.h5)
//
//            Spacer(modifier = Modifier.height(16.dp)) // Добавляем отступ
//
//            // Кнопка, изменяющая текст
//            Button(onClick = { text = "Кнопка нажата!" }) {
//                Text("Нажми меня")
//            }
//        }
//    }
//}
//@Composable
//fun MyApp() {
//    MaterialTheme {
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewMyApp() {
//    MyApp()
//}
