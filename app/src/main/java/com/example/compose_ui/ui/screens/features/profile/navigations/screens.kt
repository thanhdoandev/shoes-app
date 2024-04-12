package com.example.compose_ui.ui.screens.features.profile.navigations

import com.example.compose_ui.ui.data.enums.EScreenName

sealed class ProfileScreen(val route: EScreenName) {
    data object Profile : ProfileScreen(EScreenName.PROFILE)
}

val PROFILE_SCREENS = listOf(
    ProfileScreen.Profile)
