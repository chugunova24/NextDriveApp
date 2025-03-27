package com.example.nextdrive.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LogoText(
    text: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "NextDrive",
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            color = Color.Gray
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLogoText() {
    LogoText(
        text = "Поможем найти твою следующую поездку"
    )
}