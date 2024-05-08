package com.example.compose_ui.ui.screens.features.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddRoad
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.commons.BannerCard
import com.example.compose_ui.ui.components.commons.ProductCard
import com.example.compose_ui.ui.components.commons.apps.SearchInput
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPLocalImage
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.vo.Category
import com.example.compose_ui.ui.data.vo.Product
import com.example.compose_ui.ui.screens.features.home.components.CategoriesTitle
import com.example.compose_ui.ui.screens.features.home.components.Category
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.size_12
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_20
import com.example.compose_ui.ui.theme.size_24
import com.example.compose_ui.ui.theme.size_32
import com.example.compose_ui.ui.theme.size_4
import com.example.compose_ui.ui.theme.size_40
import com.example.compose_ui.ui.theme.size_6
import com.example.compose_ui.ui.theme.size_8

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    onViewDetail: (id: String) -> Unit = {},
    onClickSearch: () -> Unit = {},
    onClickCart: () -> Unit = {}
) {
    viewModel.run {
        HomeScreen(
            isLoadingCategories = isLoadingCategories.collectAsState().value,
            isLoadingProducts = isLoadingProducts.collectAsState().value,
            products = products.collectAsState().value,
            categories = categories.collectAsState().value,
            onDetailProduct = onViewDetail
        )
    }
}

@Composable
private fun HomeScreen(
    isLoadingCategories: Boolean = false,
    isLoadingProducts: Boolean = false,
    products: MutableList<Product> = mutableListOf(),
    categories: MutableList<Category> = mutableListOf(),
    onDetailProduct: (id: String) -> Unit = {}
) {
    ContainerPage {
        JPCard(
            roundBottomEnd = size_16,
            roundBottomStart = size_16,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            ConstraintLayout(
                Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                val (menu, notification, search, title) = createRefs()
                JPIcon(
                    Modifier.constrainAs(menu) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                    icon = Icons.Default.Menu,
                    size = size_40,
                    mTop = size_12
                )
                JPRow(
                    modifier = Modifier
                        .constrainAs(title) {
                            start.linkTo(menu.end)
                            end.linkTo(notification.start)
                        }
                        .fillMaxWidth(),
                    isCenterHoz = true,
                    mTop = size_4
                ) {
                    JPLocalImage(
                        url = R.drawable.ic_first_intro_2,
                        size = size_24
                    )
                    JPText(
                        text = stringResource(id = R.string.homeExplore),
                        style = typography.titleLarge,
                        mTop = size_6
                    )
                }
                JPIcon(
                    Modifier.constrainAs(notification) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                    icon = Icons.Default.Notifications,
                    size = size_32,
                    mTop = size_12
                )
                JPRow(
                    Modifier
                        .constrainAs(search) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(size_16),
                    isCenterVer = true,
                    isCenterHoz = true
                ) {
                    SearchInput(
                        onValueChange = {},
                        color = Color.White,
                        txtColor = Color.White,
                        isEnabled = false,
                        modifier = Modifier.wrapContentWidth()
                    )
                    JPIcon(
                        icon = Icons.Default.AddRoad,
                        size = size_32,
                        mStart = size_16,
                        mTop = size_4
                    )
                }
            }
        }
        JPColumn(Modifier.verticalScroll(rememberScrollState())) {
            JPSpacer(h = size_20)
            LazyRow(Modifier.padding(none, size_8)) {
                items(if (isLoadingCategories) 5 else categories.size) {
                    Category(
                        categories.getOrNull(it)?.name.toString(),
                        isLoading = isLoadingCategories
                    )
                }
            }
            CategoriesTitle(title = R.string.homePopularShoes, actionTitle = R.string.homeSeeAll) {}
            LazyRow(Modifier.padding(none, size_8)) {
                items(if (isLoadingProducts) 3 else products.size) { position ->
                    ProductCard(
                        product = products.getOrNull(position),
                        onViewDetail = {
                            onDetailProduct(products.getOrNull(position)?.id.toString())
                        },
                        onAddToCart = {},
                        isLoading = isLoadingProducts
                    )
                }
            }
            CategoriesTitle(title = R.string.homeNewArrivals, actionTitle = R.string.homeSeeAll) {

            }
            JPSpacer(h = size_6)
            BannerCard()
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomePreview() {
    HomeScreen {

    }
}