package com.example.compose_ui.ui.screens.features.menus.settings.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.screens.features.menus.settings.settings.Settings

fun NavGraphBuilder.settingGraph(navController: NavHostController, onOpenMenu: () -> Unit) {
    navigation(
        startDestination = getScreenName(EScreenName.SETTINGS),
        route = getScreenName(EScreenName.SETTING_ROUTER)
    ) {
        SETTING_SCREENS.forEach { screen ->
            composable(route = getScreenName(screen.route)) {
                when (screen.route) {
                    EScreenName.SETTINGS -> {
                        Settings {
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