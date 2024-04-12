package com.example.compose_ui.ui.screens.features.favorites.navigations


import com.example.compose_ui.ui.data.enums.EScreenName

sealed class FavoriteScreen(val route: EScreenName) {
    data object Favorites : FavoriteScreen(EScreenName.FAVORITES)
}

val FAVORITE_SCREENS = listOf(
    FavoriteScreen.Favorites
)
