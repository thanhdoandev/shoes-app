package com.example.compose_ui.ui.screens.intro.navigations

import com.example.compose_ui.ui.cores.data.enums.EScreenName

sealed class IntroScreens(val route: EScreenName) {
    data object Intro : IntroScreens(EScreenName.INTRO_ROUTE)
}

val INTRO_SCREENS = listOf(
    IntroScreens.Intro,
)
