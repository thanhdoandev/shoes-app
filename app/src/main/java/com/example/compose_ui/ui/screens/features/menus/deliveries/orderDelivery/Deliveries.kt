package com.example.compose_ui.ui.screens.features.menus.deliveries.orderDelivery

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.components.bases.ContainerPage

@Composable
fun Deliveries(onOpenMenu: () -> Unit) {
    DeliveryScreen {
        onOpenMenu()
    }
}

@Composable
private fun DeliveryScreen(onOpenMenu: () -> Unit = {}) {
    ContainerPage("Deliveries", isMenu = true, onBackScreen = onOpenMenu) {

    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun DeliveryPreview() {
    DeliveryScreen()
}