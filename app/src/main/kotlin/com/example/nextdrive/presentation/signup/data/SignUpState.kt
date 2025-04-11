package com.example.nextdrive.presentation.signup.data

import android.net.Uri
import android.util.Patterns

import com.example.nextdrive.utils.datePattern


data class SignUpState(
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var passwordVisible: Boolean = false,
    var confirmPasswordVisible: Boolean = false,
    var isTermsChecked: Boolean = false,
    var errorMessage: String? = null,

    var lastName: String = "",
    var firstName: String = "",
    var middleName: String = "",
    var birthDate: String = "",
    var gender: Gender = Gender.MALE,

    var licenseNumber: String = "",
    var issueDate: String = "",
    val profilePhoto: Uri? = null,
    val licensePhoto: Uri? = null,
    val passportPhoto: Uri? = null,

    var userId: String? = ""
) {
    val isFormValid: Boolean
        get() = isValidEmail(email) &&
                isPasswordValid(password) &&
                arePasswordsMatching(password, confirmPassword) &&
                isTermsChecked

    // Валидация email
    fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    // Валидация пароля
    fun isPasswordValid(password: String): Boolean =
        password.length >= 8

    // Проверка совпадения паролей
    fun arePasswordsMatching(p1: String, p2: String): Boolean =
        p1 == p2 && p1.isNotEmpty()

    // Валидация даты рождения
    fun isDateValid(date: String): Boolean {
        return if (
            date.isBlank() && date.matches(Regex(datePattern))
        ) false
        else {
            true
        }
    }

    fun areDocumentsValid(): Boolean {
        return licenseNumber.isNotBlank() &&
                issueDate.matches(Regex(datePattern)) &&
                licensePhoto != null &&
                passportPhoto != null
    }
}