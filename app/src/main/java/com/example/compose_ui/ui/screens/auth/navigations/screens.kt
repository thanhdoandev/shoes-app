package com.example.compose_ui.ui.screens.auth.navigations

import com.example.compose_ui.ui.data.enums.EScreenName

sealed class AuthScreens(val route: EScreenName) {
    data object Login : AuthScreens(EScreenName.LOGIN)
    data object Register : AuthScreens(EScreenName.REGISTER)
}

val AUTH_SCREENS = listOf(
    AuthScreens.Login,
    AuthScreens.Register
)
