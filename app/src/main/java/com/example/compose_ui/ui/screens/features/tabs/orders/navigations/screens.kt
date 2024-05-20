package com.example.compose_ui.ui.screens.features.tabs.orders.navigations

import com.example.compose_ui.ui.data.enums.EScreenName

sealed class OrderScreen(val route: EScreenName) {
    data object Orders : OrderScreen(EScreenName.ORDERS)
}

val ORDER_SCREENS = listOf(
    OrderScreen.Orders,
)
