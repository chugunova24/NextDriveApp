package com.example.nextdrive.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import androidx.compose.ui.res.painterResource
import com.example.nextdrive.R


@Composable
fun SignUpStep3Screen(navController: NavController) {
    var profilePhotoUri by remember { mutableStateOf<Uri?>(null) }
    var licensePhotoUri by remember { mutableStateOf<Uri?>(null) }
    var passportPhotoUri by remember { mutableStateOf<Uri?>(null) }
    var licenseNumber by remember { mutableStateOf("") }
    var issueDate by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var currentUploadTarget by remember { mutableStateOf("") }

    val isValidLicenseNumber = licenseNumber.isNotBlank()
    val isValidIssueDate = issueDate.matches(Regex("\\d{2}.\\d{2}.\\d{4}")) // DD/MM/YYYY
    val isFormValid = isValidLicenseNumber && isValidIssueDate && licensePhotoUri != null && passportPhotoUri != null

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            when (currentUploadTarget) {
                "profile" -> profilePhotoUri = it
                "license" -> licensePhotoUri = it
                "passport" -> passportPhotoUri = it
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Верхняя панель с кнопкой Назад
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Создать аккаунт",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 50.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        if (profilePhotoUri != null) {
            Image(
                painter = rememberImagePainter(profilePhotoUri),
                contentDescription = "Profile photo",
                modifier = Modifier.fillMaxWidth().size(128.dp),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Add profile photo",
                modifier = Modifier.fillMaxWidth().size(128.dp)
            )
        }


        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Добавление фотографии поможет владельцам и арендаторам узнать друг друга," +
                    " когда они будут забирать машину",
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Поле ввода номера водительского удостоверения
        Text(text = "Номер водительского удостоверения")
        OutlinedTextField(
            value = licenseNumber,
            onValueChange = { licenseNumber = it },
            label = { Text("0000000000") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле ввода даты выдачи
        Text(text = "Дата выдачи")
        OutlinedTextField(
            value = issueDate,
            onValueChange = { issueDate = it },
            label = { Text("DD.MM.YYYY") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Загрузка фото водительского удостоверения
        Text(text = "Загрузите фото водительского удостоверения")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    currentUploadTarget = "license"
                    launcher.launch("image/*")
                },
                modifier = Modifier.size(50.dp)
            ) {
                if (licensePhotoUri != null) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "License photo uploaded",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(32.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.upload),
                        contentDescription = "Upload"
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Загрузить фото")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Загрузка фото паспорта
        Text(text = "Загрузите фото паспорта")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    currentUploadTarget = "passport"
                    launcher.launch("image/*")
                },
                modifier = Modifier.size(50.dp)
            ) {
                if (passportPhotoUri != null) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Passport photo uploaded",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(32.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.upload),
                        contentDescription = "Upload"
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Загрузить фото")
        }


        Spacer(modifier = Modifier.height(16.dp))

        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        Spacer(modifier = Modifier.weight(1f))

        // Кнопка "Далее"
        Button(
            onClick = {
                if (isFormValid) {
                    navController.navigate("success_signup_screen")
                } else {
                    errorMessage = "Пожалуйста заполните все обязательные поля."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2A1246))
        ) {
            Text(text = "Далее")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSignUpStep3Screen() {
    SignUpStep3Screen(
        rememberNavController()
    )
}