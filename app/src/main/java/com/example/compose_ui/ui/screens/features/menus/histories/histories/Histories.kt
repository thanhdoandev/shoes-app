package com.example.compose_ui.ui.screens.features.menus.histories.histories

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.R
@Composable
fun Histories(onOpenMenu: () -> Unit) {
    HistoryScreen {
        onOpenMenu()
    }
}

@Composable
private fun HistoryScreen(onOpenMenu: () -> Unit = {}) {
    ContainerPage(stringResource(id = R.string.historyOrder), isMenu = true, onBackScreen = onOpenMenu) {

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun HistoryScreenPreview() {
    HistoryScreen()
}