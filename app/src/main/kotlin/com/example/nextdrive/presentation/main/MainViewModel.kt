package com.example.nextdrive.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.example.nextdrive.data.Car
import com.example.nextdrive.domain.car.CarRepository


class MainViewModel(
    private val carRepository: CarRepository
) : ViewModel() {
    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadCars()
    }

    fun loadCars() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _cars.value = carRepository.getAllCars()
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchCars(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _cars.value = if (query.isBlank()) {
                    carRepository.getAllCars()
                } else {
                    carRepository.searchCars(query.trim())
                }
            } catch (e: Exception) {
                _error.value = "Ошибка поиска: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}