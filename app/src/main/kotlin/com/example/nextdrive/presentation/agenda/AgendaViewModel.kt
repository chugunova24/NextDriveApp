package com.example.nextdrive.presentation.agenda

import com.example.nextdrive.common.mvi.MVIConfiguration
import com.example.nextdrive.common.mvi.MVIScreenModel
import com.example.nextdrive.presentation.agenda.model.AgendaEvent
import com.example.nextdrive.presentation.agenda.model.AgendaState



internal class AgendaViewModel : MVIScreenModel<
        AgendaEvent,
        AgendaState>(
    MVIConfiguration(initial = AgendaState.Loading)
) {
    override suspend fun onEvent(event: AgendaEvent) {
        when (event) {
            AgendaEvent.OnStart -> handleStart()
            AgendaEvent.OnClickProfile -> handleClickProfile()
            AgendaEvent.OnClickAddAgendaItem -> handleClickAddAgendaItem()
            is AgendaEvent.OnChoseDay -> handleChoseDay(event.dayOffset)
            is AgendaEvent.OnClickItem -> handleClickItem(event.id)
            is AgendaEvent.OnClickMore -> handleClickMore(event.id)
        }
    }

    private fun handleChoseDay(dayOffset: Int) {
        // TODO
    }

    private fun handleClickAddAgendaItem() {
        // TODO
    }

    private fun handleClickItem(id: String) {
        // TODO
    }

    private fun handleClickMore(id: String) {
        // TODO
    }

    private suspend fun handleStart() {
        /* TODO */
    }

    private suspend fun handleClickProfile() {
        // TODO
    }
}