package com.example.compose_ui.ui.screens.features.favorites.navigations

import android.annotation.SuppressLint
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.navigations.getCurrentRoute
import com.example.compose_ui.ui.screens.features.favorites.Favorites

fun NavGraphBuilder.favoriteGraph(navController: NavHostController) {
    navigation(
        startDestination = getScreenName(EScreenName.FAVORITES),
        route = getScreenName(EScreenName.FAVORITES_ROUTE)
    ) {
        FAVORITE_SCREENS.forEach { screen ->
            composable(route = getScreenName(screen.route)) {
//                onBottomTabState(navController.getCurrentRoute() == getScreenName(EScreenName.FAVORITES))
                when (screen.route) {
                    EScreenName.FAVORITES -> {
                        Favorites {
                            navController.navigate("${getScreenName(EScreenName.SHOES)}$it")
                        }
                    }

                    else -> {
                    }
                }
            }
        }
    }
}
