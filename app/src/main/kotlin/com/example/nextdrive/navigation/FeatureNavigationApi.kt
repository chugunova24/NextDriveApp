package com.example.nextdrive.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Feature navigation contract.
 * It provides routes, defined in register method, to feature screens from another modules
 */
interface FeatureNavigationApi {

    /**
     * Registers feature navigation graph either as separate screens via navGraphBuilder.composable(...),
     * or as a nested graph via navGraphBuilder.navigation(..)
     */
    fun NavGraphBuilder.register(
        navController: NavHostController,
        modifier: Modifier = Modifier
    )
}

const val navigationPrefix = "nextdrive://"