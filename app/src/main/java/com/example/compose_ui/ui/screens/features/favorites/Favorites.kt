package com.example.compose_ui.ui.screens.features.favorites

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
import com.example.compose_ui.ui.components.cores.JPImage
import com.example.compose_ui.ui.theme.bgLoadingColor
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
    ContainerPage(
        isBack = false,
        title = "Favorites"
    ) {
        LinearProgressIndicator(
            color = bgPage,
            trackColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
        JPImage(url = "https://www.courir.com/on/demandware.static/-/Sites-master-catalog-courir/default/dw9a359d7b/images/hi-res/001501168_101.png")

//        LazyVerticalGrid(
//            modifier = Modifier.fillMaxSize(),
//            columns = GridCells.Fixed(2),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            contentPadding = PaddingValues(0.dp),
//            horizontalArrangement = Arrangement.spacedBy(0.dp)
//        ) {
//            items(favoriteList) { product ->
//                ProductCard(
//                    product = product,
//                    onViewDetail = { onViewDetail(product.id) },
//                    onAddToCart = {},
//                    isFromFavorite = true
//                )
//            }
//        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun FavoritesPreview() {
    Favorites()
}