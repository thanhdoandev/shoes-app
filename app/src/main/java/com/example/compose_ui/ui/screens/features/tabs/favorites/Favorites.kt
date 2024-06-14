package com.example.compose_ui.ui.screens.features.tabs.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.bases.ContainerPage

@Composable
fun Favorites(
    viewModel: FavoritesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onViewDetail: (id: String) -> Unit = {}
) {
    val favoriteList by viewModel.favorites.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getFavorites()
    }
    ContainerPage(title = "Favorites", isBack = false) {
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun FavoritesPreview() {
    Favorites()
}