package com.example.nextdrive.presentation.signup.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

import com.example.nextdrive.presentation.signup.SignUpViewModel
import com.example.nextdrive.presentation.signup.data.SignUpState


@Composable
fun Step3Content(
    data: SignUpState,
    viewModel: SignUpViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Лаунчеры для разных типов документов
    val profileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> uri?.let { viewModel.updateData { copy(profilePhoto = it) } } }
    )
    val licenseLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> uri?.let { viewModel.updateData { copy(licensePhoto = it) } } }
    )
    val passportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> uri?.let { viewModel.updateData { copy(passportPhoto = it) } } }
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Аватар пользователя
        Box(
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            if (data.profilePhoto is Uri) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(data.profilePhoto)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Аватар",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                )
            }

            IconButton(
                onClick = { profileLauncher.launch("image/*") },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить фото",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }


        // Номер и дата выдачи
        OutlinedTextField(
            value = data.licenseNumber,
            onValueChange = { viewModel.updateData { copy(licenseNumber = it) } },
            label = { Text("Номер водительского удостоверения") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = data.issueDate,
            onValueChange = {
                if (it.length <= 10) {
                    viewModel.updateData { copy(issueDate = it) }
                }
            },
            label = { Text("Дата выдачи (DD.MM.YYYY)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                IconButton(onClick = { /* Открыть date picker */ }) {
                    Icon(Icons.Default.CalendarToday, null)
                }
            }
        )


        Text(text = "Загрузите документы:")

        // Водительское удостоверение
        DocumentUploadSection(
            title = "Водительское удостоверение",
            photoUri = data.licensePhoto as? Uri,
            onAddClick = { licenseLauncher.launch("image/*") },
            onRemoveClick = { viewModel.updateData { copy(licensePhoto = null) } }
        )

        // Паспорт
        DocumentUploadSection(
            title = "Паспорт (разворот с фото)",
            photoUri = data.passportPhoto as? Uri,
            onAddClick = { passportLauncher.launch("image/*") },
            onRemoveClick = { viewModel.updateData { copy(passportPhoto = null) } }
        )

        Spacer(Modifier.weight(1f))

        PrimaryButton(
            text = "Завершить регистрацию",
            enabled = data.areDocumentsValid(),
            onClick = { viewModel.completeRegistration() },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
private fun DocumentUploadSection(
    title: String,
    photoUri: Uri?,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )

        if (photoUri != null) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(photoUri)
                        .crossfade(true)
                        .build(),
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                IconButton(
                    onClick = onRemoveClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(
                            color = MaterialTheme.colorScheme.errorContainer,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Удалить",
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        } else {
            OutlinedButton(
                onClick = onAddClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Добавить фото")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpStep3Content() {
    Step3Content(
        data = SignUpState(),
        viewModel = SignUpViewModel()
    )
}