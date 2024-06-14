package com.example.compose_ui.ui.screens.features.tabs.home.notifications

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.bases.ContainerPage

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