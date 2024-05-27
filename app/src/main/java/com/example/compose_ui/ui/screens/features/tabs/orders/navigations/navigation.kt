package com.example.compose_ui.ui.screens.features.tabs.orders.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.screens.features.tabs.orders.Orders

fun NavGraphBuilder.orderGraph(navController: NavHostController) {
    navigation(
        startDestination = getScreenName(EScreenName.ORDERS),
        route = getScreenName(EScreenName.ORDERS_ROUTE)
    ) {
        ORDER_SCREENS.forEach { screen ->
            composable(route = getScreenName(screen.route)) {
                when (screen.route) {
                    EScreenName.ORDERS -> {
                        Orders()
                    }

                    else -> {
                    }
                }
            }
        }
    }
}