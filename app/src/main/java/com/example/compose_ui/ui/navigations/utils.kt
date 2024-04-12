package com.example.compose_ui.ui.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.example.compose_ui.ui.data.enums.EScreenName

fun NavController.pushToScreen(route: EScreenName, navOptions: NavOptions? = null) {
    navigate(EScreenName.getScreenName(route), navOptions)
}

fun NavController.startNewDestination(route: EScreenName) {
    navigate(EScreenName.getScreenName(route)) {
        popUpTo(graph.findStartDestination().id) {
            inclusive = true
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.getCurrentRoute(): String? = currentBackStackEntry?.destination?.route
