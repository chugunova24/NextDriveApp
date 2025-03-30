package com.example.nextdrive.domain.authentication

import kotlinx.coroutines.flow.SharedFlow
import my.zukoap.tasky.feature.authentication.api.domain.model.UserSessionEvent

/**
 * Allows you to manage the session - work with the authorization state (log out of the profile),
 * subscribe to access level status
 */
interface SessionManager {
    val events: SharedFlow<UserSessionEvent>

    suspend fun logout()
}