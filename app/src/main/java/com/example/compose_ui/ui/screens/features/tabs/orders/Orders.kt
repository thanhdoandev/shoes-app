package com.example.compose_ui.ui.screens.features.tabs.orders

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.bases.ContainerPage

@Composable
fun Orders() {
    ContainerPage(isBack = false, title = "Your Orders") {
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun OrdersPreview() {
    Orders()
}