package com.example.nextdrive.presentation

import androidx.lifecycle.ViewModel

import com.example.nextdrive.common.state.SessionStateInteractor

//import my.zukoap.tasky.domain.SessionStateInteractor

internal class MainViewModel(interactor: SessionStateInteractor) : ViewModel() {
    val isAuthenticated = interactor.isAuthenticated
}