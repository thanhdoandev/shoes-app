package com.example.compose_ui.ui.screens.features.profile.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.navigations.startNewDestination
import com.example.compose_ui.ui.screens.features.profile.Profile

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation(
        startDestination = getScreenName(EScreenName.PROFILE),
        route = getScreenName(EScreenName.PROFILE_ROUTE)
    ) {
        PROFILE_SCREENS.forEach { screen ->
            composable(route = getScreenName(screen.route)) {
                when (screen.route) {
                    EScreenName.PROFILE -> {
                        Profile {
                            navController.startNewDestination(EScreenName.AUTH_ROUTE)
                        }
                    }

                    else -> {

                    }
                }
            }
        }
    }
}
