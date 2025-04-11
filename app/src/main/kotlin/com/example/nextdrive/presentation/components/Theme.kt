package com.example.nextdrive.presentation.components


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


// Определение цветовых схем для светлой и темной тем
private val LightColors = lightColorScheme(
    // Настройки цветов для светлой темы
)

private val DarkColors = darkColorScheme(
    // Настройки цветов для темной темы
)

@Composable
fun NextDriveTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
//        typography = Typography,
        content = content
    )
}
