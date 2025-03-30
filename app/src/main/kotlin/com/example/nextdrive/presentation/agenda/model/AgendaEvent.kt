package com.example.nextdrive.presentation.agenda.model

internal sealed interface AgendaEvent {
    data object OnStart : AgendaEvent
    data object OnClickProfile : AgendaEvent
    data object OnClickAddAgendaItem : AgendaEvent
    data class OnChoseDay(val dayOffset: Int) : AgendaEvent
    data class OnClickItem(val id: String) : AgendaEvent
    data class OnClickMore(val id: String) : AgendaEvent
}