package com.example.nextdrive.navigation

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

import com.example.nextdrive.presentation.agenda.AgendaScreen


internal class AgendaFeatureNavigationImpl : AgendaFeatureNavigation {
    override val agendaMainRoute = "$navigationPrefix/agenda"
    private val agendaTaskRoute = "$agendaMainRoute/task"
    private val agendaEventRoute = "$agendaMainRoute/event"
    private val agendaReminderRoute = "$agendaMainRoute/reminder"

    override fun NavGraphBuilder.register(navController: NavHostController, modifier: Modifier) {
        composable(route = agendaMainRoute) {
            AgendaScreen(
                modifier = modifier,
//                onNavigateToTask = { navController.navigate(agendaTaskRoute) },
//                onNavigateToEvent = { navController.navigate(agendaEventRoute) },
//                onNavigateToReminder = { navController.navigate(agendaReminderRoute) },
            )
        }

        composable(route = agendaTaskRoute) {
            Text(text = agendaTaskRoute)
        }

        composable(route = agendaEventRoute) {
            Text(text = agendaTaskRoute)
        }

        composable(route = agendaReminderRoute) {
            Text(text = agendaTaskRoute)
        }
    }
}