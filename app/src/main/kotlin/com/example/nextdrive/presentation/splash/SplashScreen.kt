package com.example.nextdrive.presentation.splash

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextdrive.presentation.components.LogoImage
import com.example.nextdrive.presentation.components.LogoText
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    onTimeout: () -> Unit = {} // По умолчанию ничего не делает
) {
    LaunchedEffect(true) {
        delay(100)  // Задержка 3 секунды
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 112.dp, start = 24.dp, end = 24.dp, bottom = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        LogoText(text = "Поможем найти твою следующую поездку")
        LogoImage()
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen() // onTimeout по умолчанию пустой, навигация не вызывается
}
