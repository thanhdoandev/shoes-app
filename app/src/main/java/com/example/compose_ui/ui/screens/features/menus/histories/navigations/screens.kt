package com.example.compose_ui.ui.screens.features.menus.histories.navigations

import com.example.compose_ui.ui.data.enums.EScreenName

sealed class HistoryScreen(val route: EScreenName) {
    data object Histories : HistoryScreen(EScreenName.HISTORY)
}

val HISTORIES_SCREEN = listOf(
    HistoryScreen.Histories,
)
