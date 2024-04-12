package com.example.compose_ui.ui.screens.features.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.commons.ProductCard

@Composable
fun Favorites(
    viewModel: FavoritesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onViewDetail: (id: Int) -> Unit = {}
) {
    val favoriteList by viewModel.favorites.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getFavorites()
    }
    ContainerPage(
        isBack = false,
        title = "Favorites",
        isScrollPage = false
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(favoriteList) { product ->
                ProductCard(
                    product = product,
                    onViewDetail = { onViewDetail(product.id) },
                    onAddToCart = {},
                    isFromFavorite = true
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun FavoritesPreview() {
    Favorites()
}