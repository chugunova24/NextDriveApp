package com.example.nextdrive.di

import com.example.nextdrive.domain.auth.AuthUseCase
import com.example.nextdrive.domain.car.CarRepository
import com.example.nextdrive.domain.storage.StorageUseCase
import com.example.nextdrive.domain.user.UserUseCase
import com.example.nextdrive.presentation.login.LoginViewModel
import com.example.nextdrive.presentation.main.MainViewModel
import com.example.nextdrive.presentation.settings.SettingsViewModel
import com.example.nextdrive.presentation.signup.SignUpViewModel
import org. koin. core. module. dsl.*
import org.koin.dsl.module

val appModule = module {
    // Общие зависимости

    // UseCase
    single { AuthUseCase() }
    single { UserUseCase() }
    single { StorageUseCase() }
//    factory { LoginState() }

    // Repository
    single { CarRepository() }

    // ViewModel
    viewModel { MainViewModel(get()) }
}

val signupModule = module {
    viewModel { SignUpViewModel() }
}

val settingsModule = module {
    // ViewModels
    viewModel { LoginViewModel(get()) }
    viewModel { SettingsViewModel(get(), get(), get() ) }
}