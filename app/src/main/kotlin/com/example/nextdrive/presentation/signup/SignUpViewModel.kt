package com.example.nextdrive.presentation.signup


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.nextdrive.data.UserProfile
import com.example.nextdrive.domain.auth.AuthUseCase
import com.example.nextdrive.domain.user.UserUseCase

import com.example.nextdrive.presentation.signup.data.SignUpState
import com.example.nextdrive.presentation.signup.data.SignUpStep
import com.example.nextdrive.utils.DateTimeUtils
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock.System


class SignUpViewModel : ViewModel() {
    private val authUseCase = AuthUseCase()
    private val userUseCase = UserUseCase()
//    private val storageRepository = StorageRepository()

    private val _currentStep = mutableStateOf(SignUpStep.STEP1)
    val currentStep: State<SignUpStep> = _currentStep

    private val _formData = mutableStateOf(SignUpState())
    val formData: State<SignUpState> get() = _formData

    fun updateData(block: SignUpState.() -> SignUpState) {
        _formData.value = _formData.value.block()
    }

    fun updateTermsChecked(isChecked: Boolean) {
        _formData.value = _formData.value.copy(isTermsChecked = isChecked)
    }

    fun clearError() {
        _formData.value.errorMessage = null
    }


    private fun getValidatedProfile(): UserProfile {
        val birthDate = DateTimeUtils.parseDateTime(_formData.value.birthDate)
            ?: throw Exception("Некорректная дата рождения. Формат: DD.MM.YYYY")

        val issueDate = DateTimeUtils.parseDateTime(_formData.value.issueDate)
            ?: throw Exception("Некорректная дата выдачи. Формат: DD.MM.YYYY")

        return UserProfile(
            createdAt = System.now(),
            lastName = _formData.value.lastName.ifBlank {
                throw Exception("Фамилия не может быть пустой")
            },
            firstName = _formData.value.firstName.ifBlank {
                throw Exception("Имя не может быть пустым")
            },
            middleName = _formData.value.middleName,
            birthDate = birthDate,
            gender = _formData.value.gender.toString(),
            licenseNumber = _formData.value.licenseNumber.ifBlank {
                throw Exception("Номер лицензии не может быть пустым")
            },
            issueDate = issueDate
        )
    }

    fun completeRegistration() {
        if (_formData.value.areDocumentsValid()) {
            viewModelScope.launch {
                try {

                    var newUserProfile =  getValidatedProfile()

                    authUseCase.signUp(
                        email = _formData.value.email,
                        password = _formData.value.password,
                    )
                    authUseCase.signIn(
                        email = _formData.value.email,
                        password = _formData.value.password,
                    )
                    newUserProfile.userId = authUseCase.getCurrentSession()?.user?.id ?:
                            throw Exception("Не удалось получить ID пользователя")

                    val createdUserProfile = authUseCase.createProfile(newUserProfile)

                    _currentStep.value = SignUpStep.SUCCESS

                } catch (e: Exception) {
                    _formData.value.errorMessage = when (e) {
                        is IllegalArgumentException -> e.message
                        else -> "Ошибка регистрации: ${e.message}"
                    }
                }
            }
        }
    }

    fun navigateToNextStep() {
        _currentStep.value = when (_currentStep.value) {
            SignUpStep.STEP1 -> SignUpStep.STEP2
            SignUpStep.STEP2 -> SignUpStep.STEP3
            SignUpStep.STEP3 -> SignUpStep.SUCCESS
            SignUpStep.SUCCESS -> SignUpStep.SUCCESS
        }
    }
    fun navigateToPreviousStep() {
        _currentStep.value = when (_currentStep.value) {
            SignUpStep.STEP2 -> SignUpStep.STEP1
            SignUpStep.STEP3 -> SignUpStep.STEP2
            else -> _currentStep.value
        }
    }
}
