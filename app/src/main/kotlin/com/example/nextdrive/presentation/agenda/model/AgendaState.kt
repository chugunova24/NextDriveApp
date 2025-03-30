package com.example.nextdrive.presentation.agenda.model

import java.time.LocalDate


internal sealed interface AgendaState {
    data object Loading : AgendaState

    data class Content(
        val userName: String,
    ) : AgendaState
}