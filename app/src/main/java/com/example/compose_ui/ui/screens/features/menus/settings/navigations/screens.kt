package com.example.compose_ui.ui.screens.features.menus.settings.navigations

import com.example.compose_ui.ui.data.enums.EScreenName

sealed class SettingScreen(val route: EScreenName) {
    data object Settings : SettingScreen(EScreenName.SETTINGS)
}

val SETTING_SCREENS = listOf(
    SettingScreen.Settings,
)
