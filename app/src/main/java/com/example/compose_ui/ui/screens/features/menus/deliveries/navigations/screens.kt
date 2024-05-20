package com.example.compose_ui.ui.screens.features.menus.deliveries.navigations

import com.example.compose_ui.ui.data.enums.EScreenName

sealed class DeliveryScreen(val route: EScreenName) {
    data object Delivery : DeliveryScreen(EScreenName.DELIVERY)
}

val DELIVERY_SCREENS = listOf(
    DeliveryScreen.Delivery,
)
