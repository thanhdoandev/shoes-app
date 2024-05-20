package com.example.compose_ui.ui.screens.features.tabs.home.notifications

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.components.bases.ContainerPage

@Composable
fun Notifications() {
    ContainerPage(isBack = false, title = "Notifications") {
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun NotificationsPreview() {
    Notifications()
}