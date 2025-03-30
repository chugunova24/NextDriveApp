package my.zukoap.tasky.feature.authentication.api.domain.model

/**
 * Tokens, provided to user
 * @property refreshToken refresh token for OAuth
 * @property accessToken access token for OAuth
 */
data class Tokens(
    val refreshToken: String? = null,
    val accessToken: String? = null
) {
    val hasRefreshToken = refreshToken != null
}