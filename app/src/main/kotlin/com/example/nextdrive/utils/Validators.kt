package com.example.nextdrive.utils

import android.util.Patterns
import com.example.nextdrive.presentation.signup.data.SignUpState


val datePattern: String = "\\d{2}.\\d{2}.\\d{4}"


fun isValidStep1(data: SignUpState): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(data.email).matches() &&
            data.password.length >= 8 &&
            data.password == data.confirmPassword &&
            data.isTermsChecked
}

fun isValidStep2(data: SignUpState): Boolean {
    return data.lastName.isNotBlank() &&
            data.firstName.isNotBlank() &&
            data.birthDate.matches(Regex(datePattern))
}

fun isValidStep3(data: SignUpState): Boolean {
    return data.areDocumentsValid()
}

