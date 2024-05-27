package com.example.compose_ui.ui.screens.features.menus.histories.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.screens.features.menus.histories.histories.Histories

fun NavGraphBuilder.historiesGraph(navController: NavHostController, onOpenMenu: () -> Unit) {
    navigation(
        startDestination = getScreenName(EScreenName.HISTORY),
        route = getScreenName(EScreenName.HISTORY_ROUTE)
    ) {
        HISTORIES_SCREEN.forEach { screen ->
            composable(route = getScreenName(screen.route)) {
                when (screen.route) {
                    EScreenName.HISTORY -> {
                        Histories {
                            onOpenMenu()
                        }
                    }

                    else -> {
                    }
                }
            }
        }
    }
}