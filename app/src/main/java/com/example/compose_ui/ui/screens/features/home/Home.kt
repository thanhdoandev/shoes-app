package com.example.compose_ui.ui.screens.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ClearAll
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.bases.SearchInput
import com.example.compose_ui.ui.components.commons.BannerCard
import com.example.compose_ui.ui.components.commons.ProductCard
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.database.localdata.Categories
import com.example.compose_ui.ui.data.database.localdata.Products
import com.example.compose_ui.ui.screens.features.home.components.CategoriesTitle
import com.example.compose_ui.ui.screens.features.home.components.Category
import com.example.compose_ui.ui.theme.primaryColor

@Composable
fun Home(
    onViewDetail: (id: Int) -> Unit = {},
    onClickSearch: () -> Unit = {},
    onClickCart: () -> Unit = {}
) {
    ContainerPage(isVisibleHeader = false, isScrollPage = true) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = CardDefaults.cardColors(
                containerColor = primaryColor,
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                JPIcon(icon = Icons.Outlined.ClearAll, size = 40.dp, onClick = { onClickCart() })
                JPText(
                    text = stringResource(id = R.string.homeExplore),
                    size = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    mTop = 16.dp
                )
                JPIcon(icon = Icons.Outlined.ShoppingCart, size = 26.dp, mTop = 6.dp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchInput(
                    onValueChange = {},
                    color = Color.White,
                    txtColor = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    isEnabled = false,
                    onClick = {
                        onClickSearch()
                    }
                )
            }
        }
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            CategoriesTitle(R.string.homeCategories)
            LazyRow {
                items(Categories) {
                    Category(it.name)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            CategoriesTitle(R.string.homePopularShoes, R.string.homeSeeAll) {
            }

            LazyRow {
                items(Products) {
                    ProductCard(it, onAddToCart = {}, onViewDetail = { id ->
                        onViewDetail(id)
                    })
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            CategoriesTitle(R.string.homeNewArrivals, R.string.homeSeeAll) {
            }
            BannerCard()
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomePreview() {
    Home {

    }
}