package com.example.nextdrive.di

import com.example.nextdrive.domain.authentication.usecase.AuthUseCase
import com.example.nextdrive.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // 1. Регистрируем AuthUseCase как синглтон (один экземпляр на всё приложение)
    single { AuthUseCase() }

    // 2. Регистрируем LoginViewModel (создаётся новый для каждого экрана)
    viewModel { LoginViewModel(get()) }

    // 3. Здесь можно добавить другие зависимости
    // single { NetworkService() }
    // single { DatabaseHelper() }
}