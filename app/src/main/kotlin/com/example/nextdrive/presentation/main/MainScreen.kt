package com.example.nextdrive.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

import com.example.nextdrive.presentation.main.ui.CarListContent
import com.example.nextdrive.presentation.components.BottomNavigationBar
import kotlinx.coroutines.delay


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = koinViewModel()
) {
    val cars by viewModel.cars.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var debouncedQuery by remember { mutableStateOf("") }

    // Дебаунс для поиска (чтобы не делать запрос на каждый символ)
    LaunchedEffect(searchQuery) {
        delay(300)
        debouncedQuery = searchQuery
    }

    // Выполняем поиск при изменении debouncedQuery
    LaunchedEffect(debouncedQuery) {
        if (debouncedQuery.isNotEmpty()) {
            viewModel.searchCars(debouncedQuery)
        } else {
            viewModel.loadCars() // Если поиск очищен - загружаем все автомобили
        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Поиск по марке, модели и типу") },
                trailingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Поиск")
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                isLoading -> LoadingIndicator()
                error != null -> ErrorMessage(error)
                else -> CarListContent(
                    cars = cars,
                    onBookClick = { carId -> navController.navigate("car_booking_screen?carId=$carId") },
                    onDetailsClick = { carId -> navController.navigate("car_details_screen?carId=$carId") }
                )
            }
        }
    }
}

@Composable
private fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorMessage(error: String?, onRetry: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(error ?: "Неизвестная ошибка")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Попробовать снова")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
}
