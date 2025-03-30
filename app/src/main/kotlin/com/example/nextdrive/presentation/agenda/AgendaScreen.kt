package com.example.nextdrive.presentation.agenda

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nextdrive.presentation.agenda.model.AgendaEvent
import org.koin.androidx.compose.koinViewModel

import com.example.nextdrive.presentation.agenda.model.AgendaState
import com.example.nextdrive.presentation.theme.ProjectTheme


@Composable
internal fun AgendaScreen(
    modifier: Modifier = Modifier,
    viewModel: AgendaViewModel = koinViewModel(),
//    onNavigateToTask: () -> Unit,
//    onNavigateToEvent: () -> Unit,
//    onNavigateToReminder: () -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    when (val stateValue = state.value) {
        is AgendaState.Content -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("AgendaState.Content")
        }

        AgendaState.Loading -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("AgendaState.Loading")
        }
    }

//    CollectActionsWithLifecycle(viewModel) { action ->
//        when (action) {
//            AgendaAction.NavigateToEvent -> onNavigateToEvent()
//            AgendaAction.NavigateToReminder -> onNavigateToReminder()
//            AgendaAction.NavigateToTask -> onNavigateToTask()
//        }
//    }

    LaunchedEffect(true) {
        viewModel.event(AgendaEvent.OnStart)
    }
}