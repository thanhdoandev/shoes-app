package com.example.compose_ui.ui.screens.features.tabs.favorites.navigations


import com.example.compose_ui.ui.cores.data.enums.EScreenName

sealed class FavoriteScreen(val route: EScreenName) {
    data object Favorites : FavoriteScreen(EScreenName.FAVORITES)
}

val FAVORITE_SCREENS = listOf(
    FavoriteScreen.Favorites
)
