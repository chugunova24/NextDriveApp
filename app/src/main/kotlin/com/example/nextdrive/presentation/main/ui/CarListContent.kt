package com.example.nextdrive.presentation.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nextdrive.data.Car


@Composable
fun CarListContent(
    cars: List<Car>,
    onBookClick: (Long) -> Unit,
    onDetailsClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = cars.size,
            key = { index -> cars[index].id }
        ) { index ->
            val car = cars[index]
            CarCard(
                car = car,
                onBookClick = { onBookClick(car.id) },
                onDetailsClick = { onDetailsClick(car.id) }
            )
        }
    }
}