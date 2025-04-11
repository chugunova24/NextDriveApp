package com.example.nextdrive.presentation.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.nextdrive.presentation.signup.SignUpViewModel
import com.example.nextdrive.presentation.signup.data.Gender
import com.example.nextdrive.presentation.signup.data.SignUpState
import com.example.nextdrive.utils.isValidStep2


@Composable
fun Step2Content(data: SignUpState, viewModel: SignUpViewModel) {
    var genderChoice by remember { mutableStateOf<Gender>(data.gender) }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = data.lastName,
            onValueChange = { newLastName ->
                viewModel.updateData {
                    copy(lastName = newLastName)
                }
            },
            label = { Text("Фамилия") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = data.firstName,
//            onValueChange = { viewModel.updateData { firstName = it } },
            onValueChange = { newFirstName ->
                viewModel.updateData {
                    copy(firstName = newFirstName)
                }
            },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = data.middleName,
//            onValueChange = { viewModel.updateData { middleName = it } },
            onValueChange = { newMiddleName ->
                viewModel.updateData {
                    copy(middleName = newMiddleName)
                }
            },
            label = { Text("Отчество") },
            modifier = Modifier.fillMaxWidth()
        )

        // 2. Добавляем поле для даты рождения
        OutlinedTextField(
            value = data.birthDate,
            onValueChange = { newDate: String ->
                if (newDate.length <= 10) { // Ограничение длины
                    viewModel.updateData { copy(birthDate = newDate) }
                }
            },
            label = { Text("Дата рождения (DD.MM.YYYY)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = data.birthDate.isNotBlank() && !data.isDateValid(data.birthDate),
//            visualTransformation = DateTransformation(),
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.CalendarToday, contentDescription = "Календарь")
                }
            }
        )

        // 3. Добавляем переключатель пола
        GenderRadioButtonSelection(
            selectedGender = genderChoice,
            onGenderSelected = {
                gender: Gender -> genderChoice = gender
                data.gender = gender
            }
        )
        PrimaryButton(
            text = "Далее",
            enabled = isValidStep2(data),
            onClick = { viewModel.navigateToNextStep() }
        )
    }
}