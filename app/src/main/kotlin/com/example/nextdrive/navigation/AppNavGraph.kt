package com.example.nextdrive.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.koin.compose.koinInject

import com.example.nextdrive.presentation.theme.ProjectTheme


@Composable
internal fun AppNavGraph(
    modifier: Modifier = Modifier,
    isAuthenticated: Boolean,
    navController: NavHostController
) {

    val authFeature = koinInject<AuthenticationFeatureApi>()
    val agendaFeature = koinInject<AgendaFeatureNavigation>()


    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) agendaFeature.agendaMainRoute else authFeature.loginScreenRoute,
        modifier = modifier.background(ProjectTheme.colors.black),
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
    ) {
        authFeature.apply { register(navController = navController) }
        agendaFeature.apply { register(navController = navController) }
    }
}
