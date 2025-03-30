package my.zukoap.tasky.feature.authentication.api.domain.model

/**
 * События сессии пользователя
 */
sealed class UserSessionEvent {
    data object PreLogout : UserSessionEvent()

    data object LoggedOut : UserSessionEvent()

    data object LoggedIn : UserSessionEvent()
}