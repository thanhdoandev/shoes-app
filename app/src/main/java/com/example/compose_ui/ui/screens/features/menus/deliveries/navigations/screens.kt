package com.example.compose_ui.ui.screens.features.menus.deliveries.navigations

import com.example.compose_ui.ui.cores.data.enums.EScreenName

sealed class DeliveryScreen(val route: EScreenName) {
    data object Delivery : DeliveryScreen(EScreenName.DELIVERY)

    data object MapView : DeliveryScreen(EScreenName.MAP_VIEW)
}

val DELIVERY_SCREENS = listOf(
    DeliveryScreen.Delivery,
    DeliveryScreen.MapView
)
