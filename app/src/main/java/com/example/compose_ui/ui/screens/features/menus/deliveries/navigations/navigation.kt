package com.example.compose_ui.ui.screens.features.menus.deliveries.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.navigations.pushToScreen
import com.example.compose_ui.ui.screens.features.menus.deliveries.maps.MapView
import com.example.compose_ui.ui.screens.features.menus.deliveries.orders.Deliveries

fun NavGraphBuilder.deliveriesGraph(navController: NavHostController, onOpenMenu: () -> Unit) {
    navigation(
        startDestination = getScreenName(EScreenName.DELIVERY),
        route = getScreenName(EScreenName.DELIVERY_ROUTER)
    ) {
        DELIVERY_SCREENS.forEach { screen ->
            composable(route = getScreenName(screen.route)) {
                when (screen.route) {
                    EScreenName.DELIVERY -> {
                        Deliveries(onOpenMap = {
                            navController.pushToScreen(EScreenName.MAP_VIEW)
                        }) {
                            onOpenMenu()
                        }
                    }

                    EScreenName.MAP_VIEW -> {
                        MapView()
                    }

                    else -> {
                    }
                }
            }
        }
    }
}