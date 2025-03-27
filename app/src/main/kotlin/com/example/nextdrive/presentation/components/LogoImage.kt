package com.example.nextdrive.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextdrive.R


@Composable
fun LogoImage() {
    Image(
        painter = painterResource(id = R.drawable.splash_logo),
        contentDescription = "Описание изображения",
        modifier = Modifier
            .size(343.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewLogoImage() {
    LogoImage()
}


