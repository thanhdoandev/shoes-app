package com.example.compose_ui.ui.screens.features.home.navigations

import com.example.compose_ui.ui.data.enums.EScreenName

sealed class HomeScreen(val route: EScreenName) {
    data object Home : HomeScreen(EScreenName.HOME)
    data object ShoesDetail : HomeScreen(EScreenName.SHOES_DETAIL)
    data object SearchScreen : HomeScreen(EScreenName.SEARCH_SCREEN)
}

val HOME_SCREENS = listOf(
    HomeScreen.Home,
    HomeScreen.ShoesDetail,
    HomeScreen.SearchScreen
)
