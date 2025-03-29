package com.example.nextdrive.presentation.screens.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SignUpView : ViewModel() {

    private val _registrationData = MutableStateFlow<Map<String, String>>(emptyMap())
    val registrationData = _registrationData.asStateFlow()

    fun updateData(key: String, value: String) {
        _registrationData.value = _registrationData.value.toMutableMap().apply {
            put(key, value)
        }
    }

    fun getData(key: String): String? {
        return _registrationData.value[key]
    }
}
