package com.example.compose_ui.ui.navigations

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.example.compose_ui.ui.data.enums.EScreenName

fun NavController.pushToScreen(route: EScreenName, navOptions: NavOptions? = null) {
    navigate(EScreenName.getScreenName(route), navOptions)
}

fun NavController.startNewDestination(route: EScreenName, isSaveSate: Boolean = true) {
    navigate(EScreenName.getScreenName(route)) {
        popUpTo(graph.findStartDestination().id) {
            inclusive = true
            saveState = isSaveSate
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavBackStackEntry?.getCurrentRoute(): String? = this?.destination?.route
