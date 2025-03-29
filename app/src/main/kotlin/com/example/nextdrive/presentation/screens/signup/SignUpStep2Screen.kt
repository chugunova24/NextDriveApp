package com.example.nextdrive.presentation.screens.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
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


@Composable
fun SignUpStep2Screen(navController: NavController, signUpView: SignUpView) {
    var lastName by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Мужской") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val isValidLastName = lastName.isNotBlank()
    val isValidFirstName = firstName.isNotBlank()
    val isValidBirthDate = birthDate.matches(Regex("\\d{2}.\\d{2}.\\d{4}"))
    val isValidGender = selectedGender.isNotBlank()

    val isFormValid = isValidLastName && isValidFirstName && isValidBirthDate && isValidGender

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

        Spacer(modifier = Modifier.height(16.dp))

        // Поле Фамилия
        Text(text = "Фамилия", modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Введите фамилию") },
            modifier = Modifier.fillMaxWidth(),
            isError = lastName.isNotBlank() && !isValidLastName
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле Имя
        Text(text = "Имя", modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Введите имя") },
            modifier = Modifier.fillMaxWidth(),
            isError = firstName.isNotBlank() && !isValidFirstName
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле Отчество
        Text(text = "Отчество", modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = middleName,
            onValueChange = { middleName = it },
            label = { Text("Введите отчество") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле Дата рождения
        Text(text = "Дата рождения", modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text("DD.MM.YYYY") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = birthDate.isNotBlank() && !isValidBirthDate,
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.CalendarToday, contentDescription = "Календарь")
                }
            }
        )

        if (birthDate.isNotBlank() && !isValidBirthDate) {
            Text(
                text = "Введите корректную дату рождения.",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Пол", modifier = Modifier.fillMaxWidth())
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.weight(1f)
            ) {
                RadioButton(
                    selected = selectedGender == "Мужской",
                    onClick = { selectedGender = "Мужской" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF2A1246),
                        unselectedColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Мужской")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.weight(1f)
            ) {
                RadioButton(
                    selected = selectedGender == "Женский",
                    onClick = { selectedGender = "Женский" },
                    colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF2A1246),
                        unselectedColor = Color.Gray
                )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Женский")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Сообщение об ошибке
        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.weight(1f))

        // Кнопка Далее
        Button(
            onClick = {
                if (isFormValid) {
                    signUpView.updateData("lastName", lastName)
                    signUpView.updateData("firstName", firstName)
                    signUpView.updateData("middleName", middleName)
                    signUpView.updateData("birthDate", birthDate)
                    signUpView.updateData("selectedGender", selectedGender)

                    navController.navigate("signup_step3_screen")
                } else {
                    errorMessage = "Пожалуйста, заполните все обязательные поля."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2A1246))
        ) {
            Text(text = "Далее", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpStep2Screen() {
    SignUpStep2Screen(
        rememberNavController(),
        SignUpView()
    )
}