package com.example.nextdrive.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable


@Composable
fun ProjectTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalProjectColors provides ProjectColors.defaultColors,
        content = content
    )
}


object ProjectTheme {
    val colors: ProjectColors
        @Composable
        @ReadOnlyComposable
        get() = LocalProjectColors.current
}
