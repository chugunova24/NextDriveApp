package com.example.nextdrive.common.state

import com.example.nextdrive.domain.authentication.SessionManager
import com.example.nextdrive.storage.KeyValueStorage
import com.example.nextdrive.storage.clear
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import my.zukoap.tasky.feature.authentication.api.domain.model.UserSessionEvent

internal class SessionStateInteractor(
    private val sessionManager: SessionManager,
    private val userContextStorage: KeyValueStorage,
    dispatcher: CoroutineDispatcher
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

//    private val logger = Logger(this)

    val isAuthenticated = MutableStateFlow<Boolean?>(null)

    init {
        scope.launch { collectUserSessionEvents() }
    }

    private suspend fun collectUserSessionEvents() {
        sessionManager.events.collect { event ->
            when (event) {
                UserSessionEvent.PreLogout -> handlePreLogout()
                UserSessionEvent.LoggedIn -> handleLoggedStateChange(true)
                UserSessionEvent.LoggedOut -> handleLoggedStateChange(false)
            }
        }
    }

    private suspend fun handleLoggedStateChange(isLoggedIn: Boolean) {
        isAuthenticated.emit(isLoggedIn)
//        logger.debug("isLoggedIn: $isLoggedIn")
    }

    /**
     * Clearing resources when the user logs out
     */
    private suspend fun handlePreLogout() {
        userContextStorage.clear()
//        logger.debug("Cleared user specific resources")
    }
}