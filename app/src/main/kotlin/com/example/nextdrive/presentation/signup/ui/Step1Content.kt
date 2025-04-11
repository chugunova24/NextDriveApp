package com.example.nextdrive.presentation.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.example.nextdrive.presentation.signup.SignUpViewModel
import com.example.nextdrive.presentation.signup.data.SignUpState
import com.example.nextdrive.utils.isValidStep1


@Composable
fun Step1Content(data: SignUpState, viewModel: SignUpViewModel) {
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Поле Email
        OutlinedTextField(
            value = data.email,
            onValueChange = { newEmail ->
                viewModel.updateData {
                    copy(email = newEmail)
                }
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = !data.isValidEmail(data.email)
        )


        // Поле Пароль
        PasswordField(
            value = data.password,
            label = "Пароль",
            isVisible = passwordVisible,
            onValueChange = { newPassword ->
                viewModel.updateData {
                    copy(password = newPassword)
                }
            },
            onVisibilityChange = { passwordVisible = it }
        )

        // Поле Подтверждение пароля
        PasswordField(
            value = data.confirmPassword,
            label = "Повторите пароль",
            isVisible = confirmPasswordVisible,
            onValueChange = { newConfirmPassword ->
                viewModel.updateData {
                    copy(confirmPassword = newConfirmPassword)
                }
            },
            onVisibilityChange = { confirmPasswordVisible = it }
        )

        // Чекбокс согласия
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = data.isTermsChecked,
                onCheckedChange = { viewModel.updateTermsChecked(it) }
            )
            Text(
                text = "Согласен с условиями",
//                modifier = Modifier.clickable {
//                    viewModel.updateData { isTermsChecked = !isTermsChecked }
//                }
            )
        }

        PrimaryButton(
            text = "Далее",
            enabled = isValidStep1(data),
            onClick = { viewModel.navigateToNextStep() }
        )
    }
}