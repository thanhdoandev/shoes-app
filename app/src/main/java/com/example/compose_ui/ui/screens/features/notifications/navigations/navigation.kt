package com.example.compose_ui.ui.screens.features.notifications.navigations

import android.annotation.SuppressLint
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.screens.features.notifications.Notifications

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun NavGraphBuilder.notificationGraph(navController: NavHostController) {
    navigation(
        startDestination = EScreenName.getScreenName(EScreenName.NOTIFICATIONS),
        route = EScreenName.getScreenName(EScreenName.NOTIFICATIONS_ROUTE)
    ) {
        NOTIFICATION_SCREENS.forEach { screen ->
            composable(route = EScreenName.getScreenName(screen.route)) {
                when (screen.route) {
                    EScreenName.NOTIFICATIONS -> {
                        Notifications()
                    }

                    else -> {
                    }
                }
            }
        }
    }
}