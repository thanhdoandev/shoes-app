package com.example.compose_ui.ui.screens.intro.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.navigations.startNewDestination
import com.example.compose_ui.ui.screens.intro.IntroScreen


fun NavGraphBuilder.introGraph(navController: NavController) {
    navigation(
        startDestination = getScreenName(EScreenName.INTRO),
        route = getScreenName(EScreenName.INTRO_ROUTE)

    ) {
        composable(getScreenName(EScreenName.INTRO)) {
            IntroScreen {
                navController.startNewDestination(EScreenName.AUTH_ROUTE)
            }
        }
    }
}