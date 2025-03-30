package com.example.nextdrive.common.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import com.example.nextdrive.common.mvi.MVIConfiguration

/**
 * Inspired by FlowMVI 2.0
 *
 * Base class for view-model that implements the MVI/MVVM+ pattern
 * @see <a href="https://developer.android.com/jetpack/compose/text/user-input#state-practices">
 * respawn-app/FlowMVI GitHub
 * </a>
 * @param E base class for external screen event (user click, receiving permission)
 * @param S base class for screen state
 * @param A base class for one-time actions that need to be played (show a popup, call a system picker)
 */
abstract class MVIScreenModel<E, S>(
    val config: MVIConfiguration<S>
) : ViewModel(),
    StateModel<S> by stateModel(config.initial),
    EventModel<E> by eventModel(
        config.parallelEventProcess,
        config.eventCapacity,
        config.onEventOverflow
    ) {

    init {
        viewModelScope.launch {
            awaitEvents(::onEvent)
        }
    }

    /**
     * Event handler. Has to be overwritten at place
     */
    protected abstract suspend fun onEvent(event: E)
}