package com.example.nextdrive.presentation.screens.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SettingsView: ViewModel() {
    private val _userProfile = MutableStateFlow(UserProfile("Имя", "email@example.com", "avatar_url"))
    val userProfile: StateFlow<UserProfile> = _userProfile

    private val _theme = MutableStateFlow(false)
    val theme: StateFlow<Boolean> = _theme

    private val _notificationsEnabled = MutableStateFlow(true)
    val notificationsEnabled: StateFlow<Boolean> = _notificationsEnabled

    fun toggleTheme() {
        _theme.value = !_theme.value
    }

    fun toggleNotifications() {
        _notificationsEnabled.value = !_notificationsEnabled.value
    }
}