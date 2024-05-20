package com.example.compose_ui.ui.screens.features.tabs.home.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.bases.UIState
import com.example.compose_ui.ui.components.commons.ProductCard
import com.example.compose_ui.ui.components.cores.JPButton
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPExText
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPImage
import com.example.compose_ui.ui.components.cores.JPLocalImage
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.vo.Product
import com.example.compose_ui.ui.data.vo.getProductPreview
import com.example.compose_ui.ui.extensions.convertToDoubleDisplay
import com.example.compose_ui.ui.screens.features.tabs.home.components.CategoriesTitle
import com.example.compose_ui.ui.theme.colorUnlike
import com.example.compose_ui.ui.theme.font_22
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.size_10
import com.example.compose_ui.ui.theme.size_2
import com.example.compose_ui.ui.theme.size_20
import com.example.compose_ui.ui.theme.size_250
import com.example.compose_ui.ui.theme.size_28
import com.example.compose_ui.ui.theme.size_32
import com.example.compose_ui.ui.theme.size_4
import com.example.compose_ui.ui.theme.size_6
import com.example.compose_ui.ui.theme.size_60

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShoesDetail(
    viewModel: ShoesDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val shoes by viewModel.shoes.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val similarShoes by viewModel.similarShoes.collectAsState()
    val isLoading by viewModel.isLoadingSimilar.collectAsState()

    ShoesDetailScreen(
        uiState,
        shoes,
        similarShoes,
        isLoading,
        onCallBack = onBack,
        onClickSimilarShoes = {
            viewModel.getShoesDetail(it)
        })
}

@Composable
private fun ShoesDetailScreen(
    uiState: UIState,
    shoes: Product?,
    similarShoes: MutableList<Product> = mutableListOf(),
    isLoading: Boolean = false,
    onCallBack: () -> Unit = {},
    onClickSimilarShoes: (id: String) -> Unit = {}
) {
    ContainerPage(
        title = "${shoes?.name}",
        onBackScreen = { onCallBack() },
        uiState = uiState
    ) {
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (content, action) = createRefs()

            JPColumn(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }) {
                shoes?.run {
                    JPColumn(pHoz = size_20) {
                        JPText(
                            mTop = size_20,
                            text = name,
                            style = typography.titleMedium.copy(
                                fontSize = font_22,
                                color = primaryColor,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        JPText(
                            mTop = size_2,
                            text = type,
                            color = colorScheme.secondary
                        )
                        JPText(
                            text = price.convertToDoubleDisplay(isCurrency = true),
                            mTop = size_6,
                            style = typography.titleMedium.copy(
                                fontSize = font_22,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        JPColumn(Modifier.paint(painterResource(id = R.drawable.img_border))) {
                            JPImage(
                                mTop = size_32,
                                modifier = Modifier.fillMaxSize(),
                                url = image,
                                size = size_250
                            )
                            JPSpacer(h = size_32)
                            LazyRow {
                                items(8) {
                                    Card(
                                        Modifier
                                            .padding(8.dp)
                                            .clickable {

                                            },
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.White,
                                        ),
                                    ) {
                                        JPLocalImage(url = R.drawable.product_3, size = 100.dp)
                                    }
                                }
                            }
                        }
                        JPExText(
                            text = description,
                            minLines = 3,
                        )
                    }
                }
                if (isLoading || similarShoes.isNotEmpty()) CategoriesTitle(title = R.string.productRelated)
                LazyRow(modifier = Modifier.padding(none, size_20)) {
                    items(if (isLoading) 3 else similarShoes.size) { position ->
                        ProductCard(
                            product = similarShoes.getOrNull(position),
                            isLoading = isLoading,
                            onViewDetail = {
                                onClickSimilarShoes(it)
                            }
                        )
                    }
                }
            }
            JPRow(
                Modifier
                    .height(size_60)
                    .constrainAs(action) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, mBottom = size_10
            ) {
                JPRow(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(), isCenterHoz = true, isCenterVer = true
                ) {
                    Box(
                        Modifier.background(colorUnlike, shape = RoundedCornerShape(100))
                    ) {
                        JPIcon(
                            icon = if (shoes?.isFavorite == true) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            size = size_28,
                            marginAll = size_4,
                            color = Color.Red
                        )
                    }
                }
                JPButton(
                    label = stringResource(id = R.string.addToCart), modifier = Modifier
                        .width(size_250)
                        .weight(1f),
                    mEnd = size_20
                ) {

                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewLoading() {
    ShoesDetailScreen(uiState = UIState(isLoading = true), shoes = getProductPreview()) {
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ShoesDetailPreview() {
    ShoesDetailScreen(uiState = UIState(), shoes = getProductPreview()) {
    }
}