package com.example.nextdrive.presentation.settings.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nextdrive.utils.toImageBitmap
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

import com.example.nextdrive.presentation.settings.ui.SettingsMenuItem
import com.example.nextdrive.presentation.settings.SettingsViewModel


@Composable
fun ProfileDetails(
    viewModel: SettingsViewModel,
    onBackClick: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var showImagePicker = remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            scope.launch {
                val bytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
                bytes?.let { viewModel.uploadProfileImage(it) }
            }
        }
    }

    LaunchedEffect(Unit) {
        val userId: String = viewModel.userProfile.userId.toString()
        viewModel.loadAvatar(userId)
    }

    if (showImagePicker.value) {
        AlertDialog(
            onDismissRequest = { showImagePicker.value = false },
            title = { Text("Выберите действие") },
            text = {
                Column {
                    Button(
                        onClick = {
                            showImagePicker.value = false
                            imagePicker.launch("image/*")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Выбрать из галереи")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { showImagePicker.value = false },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text("Отмена")
                    }
                }
            },
            confirmButton = {}
        )
    }

    val joinDate = remember {
        try {
            val date = when (val createdAt = viewModel.userProfile.createdAt) {
                is String -> Instant.parse(createdAt)
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                is Instant -> createdAt.toLocalDateTime(TimeZone.currentSystemDefault())
                else -> throw IllegalArgumentException("Unsupported date format")
            }

            val month = when (date.monthNumber) {
                1 -> "январе"
                2 -> "феврале"
                3 -> "марте"
                4 -> "апреле"
                5 -> "мае"
                6 -> "июне"
                7 -> "июле"
                8 -> "августе"
                9 -> "сентябре"
                10 -> "октябре"
                11 -> "ноябре"
                12 -> "декабре"
                else -> ""
            }
            "Присоединился в $month ${date.year}"
        } catch (e: Exception) {
            "Дата присоединения неизвестна"
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onBackClick() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Назад",
                modifier = Modifier.clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Профиль",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable { showImagePicker.value = true },
                    contentAlignment = Alignment.Center
                ) {
                    if (viewModel.avatarBytes != null) {
                        Image(
                            bitmap = viewModel.avatarBytes!!.toImageBitmap(),
                            contentDescription = "User Avatar",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Нажмите для смены аватара",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                viewModel.avatarError?.let { error ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "${viewModel.userProfile.firstName} ${viewModel.userProfile.lastName}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = joinDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            SettingsMenuItem(
                icon = Icons.Default.Email,
                title = "Электронная почта",
                trailingContent = {
                    Text(
                        text = viewModel.userEmail,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            SettingsMenuItem(
                icon = Icons.Default.Lock,
                title = "Поменять пароль",
                onClick = { /* TODO */ }
            )

            SettingsMenuItem(
                icon = Icons.Default.Person,
                title = "Пол",
                trailingContent = {
                    Text(
                        text = when (viewModel.userProfile.gender) {
                            "MALE" -> "Мужчина"
                            "FEMALE" -> "Женщина"
                            else -> "Другое"
                        },
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            SettingsMenuItem(
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                title = "Выйти из профиля",
                onClick = {
                    viewModel.userLogout()
                    navController.navigate("login_or_signup_screen") {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}


//@Composable
//fun ProfileDetails(
//    viewModel: SettingsViewModel,
//    onBackClick: () -> Unit,
//    navController: NavController
//) {
//    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
//    var showImagePicker = remember { mutableStateOf(false) }
//
//    // Обработчик выбора изображения
//    val imagePicker = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri ->
//        uri?.let {
//            scope.launch {
//                val bytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
//                bytes?.let { viewModel.uploadProfileImage(it) }
//            }
//        }
//    }
//
//    // Загружаем аватар при открытии профиля
//    LaunchedEffect(Unit) {
//        val userId: String = viewModel.userProfile.userId.toString()
//        viewModel.loadAvatar(userId)
//    }
//
//    // Показываем диалог выбора изображения
//    if (showImagePicker.value) {
//        AlertDialog(
//            onDismissRequest = { showImagePicker.value = false },
//            title = { Text("Выберите действие") },
//            text = {
//                Column {
//                    Button(
//                        onClick = {
//                            showImagePicker.value = false
//                            imagePicker.launch("image/*")
//                        },
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Text("Выбрать из галереи")
//                    }
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Button(
//                        onClick = { showImagePicker.value = false },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = MaterialTheme.colorScheme.errorContainer
//                        )
//                    ) {
//                        Text("Отмена")
//                    }
//                }
//            },
//            confirmButton = {}
//        )
//    }
//
//    val joinDate = remember {
//
//        try {
//            // Parse the date - adjust based on your actual createdAt format
//            val date = when (val createdAt = viewModel.userProfile.createdAt) {
//                is String -> Instant.parse(createdAt)
//                    .toLocalDateTime(TimeZone.currentSystemDefault())
//
//                is Instant -> createdAt.toLocalDateTime(TimeZone.currentSystemDefault())
//                else -> throw IllegalArgumentException("Unsupported date format")
//            }
//
//            val month = when (date.monthNumber) {  // monthNumber is Int (1-12)
//                1 -> "январе"
//                2 -> "феврале"
//                3 -> "марте"
//                4 -> "апреле"
//                5 -> "мае"
//                6 -> "июне"
//                7 -> "июле"
//                8 -> "августе"
//                9 -> "сентябре"
//                10 -> "октябре"
//                11 -> "ноябре"
//                12 -> "декабре"
//                else -> ""
//            }
//            "Присоединился в $month ${date.year}"
//        } catch (e: Exception) {
//            "Дата присоединения неизвестна"
//        }
//    }
//
//
//    Column {
//        // Шапка с кнопкой назад
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { onBackClick() }
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                contentDescription = "Назад",
//                modifier = Modifier.clickable { onBackClick() }
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Text(
//                text = "Профиль",
//                style = MaterialTheme.typography.headlineMedium
//            )
//        }
//
//        // Блок с аватаром
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clickable { showImagePicker.value = true },
//                    contentAlignment = Alignment.Center
//                ) {
//                    if (viewModel.avatarBytes != null) {
//                        Image(
//                            bitmap = viewModel.avatarBytes!!.toImageBitmap(),
//                            contentDescription = "User Avatar",
//                            modifier = Modifier.fillMaxSize(),
//                            contentScale = ContentScale.Crop
//                        )
//                    } else {
//                        Icon(
//                            imageVector = Icons.Default.Person,
//                            contentDescription = "Profile",
//                            modifier = Modifier.size(48.dp)
//                        )
//                    }
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = "Нажмите для смены аватара",
//                    style = MaterialTheme.typography.bodySmall,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                )
//
//                viewModel.avatarError?.let { error ->
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = error,
//                        color = MaterialTheme.colorScheme.error,
//                        style = MaterialTheme.typography.bodySmall
//                    )
//                }
//            }
//
//            // Основная информация
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text(
//                        text = "${viewModel.userProfile.firstName} ${viewModel.userProfile.lastName}",
//                        style = MaterialTheme.typography.headlineSmall
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = joinDate,
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                }
//            }
//
//            // Меню профиля
//            Column(modifier = Modifier.padding(vertical = 8.dp)) {
//                SettingsMenuItem(
//                    icon = Icons.Default.Email,
//                    title = "Электронная почта",
//                    trailingContent = {
//                        Text(
//                            text = viewModel.userEmail,
//                            color = MaterialTheme.colorScheme.onSurfaceVariant
//                        )
//                    }
//                )
//
//                SettingsMenuItem(
//                    icon = Icons.Default.Lock,
//                    title = "Поменять пароль",
//                    onClick = { /* TODO */ }
//                )
//
//                SettingsMenuItem(
//                    icon = Icons.Default.Person,
//                    title = "Пол",
//                    trailingContent = {
//                        Text(
//                            text = when (viewModel.userProfile.gender) {
//                                "MALE" -> "Мужчина"
//                                "FEMALE" -> "Женщина"
//                                else -> {
//                                    "Другое"
//                                }
//                            },
//                            color = MaterialTheme.colorScheme.onSurfaceVariant
//                        )
//                    }
//                )
//
//                SettingsMenuItem(
//                    icon = Icons.AutoMirrored.Filled.ExitToApp,
//                    title = "Выйти из профиля",
//                    onClick = {
//                        viewModel.userLogout()
//                        navController.navigate("login_or_signup_screen") {
//                            popUpTo(navController.graph.id) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                )
//            }
//        }
//    }
//}