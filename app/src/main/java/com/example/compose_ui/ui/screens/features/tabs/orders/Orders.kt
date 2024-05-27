package com.example.compose_ui.ui.screens.features.tabs.orders

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.components.bases.ContainerPage

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