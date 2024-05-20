package com.example.compose_ui.ui.screens.features.tabs.favorites

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.theme.bgPage

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
        LinearProgressIndicator(
            color = bgPage,
            trackColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun FavoritesPreview() {
    Favorites()
}