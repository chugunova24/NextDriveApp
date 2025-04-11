package com.example.nextdrive.presentation.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

import com.example.nextdrive.presentation.signup.data.Gender


@Composable
fun GenderRadioButtonSelection(
    modifier: Modifier = Modifier,
    selectedGender: Gender,
    onGenderSelected: (Gender) -> Unit
) {
    Column(modifier.selectableGroup()) {
        Gender.values().forEach { gender ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (gender == selectedGender),
                        onClick = { onGenderSelected(gender) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (gender == selectedGender),
                    onClick = null // null recommended for accessibility
                )
                Text(
                    text = when (gender) {
                        Gender.MALE -> "Мужчина"
                        Gender.FEMALE -> "Женщина"
                        Gender.OTHER -> "Другое"
                    },
//                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}