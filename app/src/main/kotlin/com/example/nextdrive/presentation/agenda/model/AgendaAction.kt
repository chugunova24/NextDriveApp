package com.example.nextdrive.presentation.agenda.model

internal sealed interface AgendaAction {
    data object NavigateToTask : AgendaAction
    data object NavigateToEvent : AgendaAction
    data object NavigateToReminder : AgendaAction
}