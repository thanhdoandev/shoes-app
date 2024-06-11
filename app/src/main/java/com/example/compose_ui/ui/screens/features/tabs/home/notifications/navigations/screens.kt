package com.example.compose_ui.ui.screens.features.tabs.home.notifications.navigations

import com.example.compose_ui.ui.cores.data.enums.EScreenName

sealed class NotificationScreen(val route: EScreenName) {
    data object Notifications : NotificationScreen(EScreenName.NOTIFICATIONS)
}

val NOTIFICATION_SCREENS = listOf(
    NotificationScreen.Notifications,
)
