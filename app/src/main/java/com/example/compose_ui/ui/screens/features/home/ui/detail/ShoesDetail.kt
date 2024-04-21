package com.example.compose_ui.ui.screens.features.home.ui.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
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
import com.example.compose_ui.ui.extensions.convertToDoubleDisplay
import com.example.compose_ui.ui.extensions.getStringValue
import com.example.compose_ui.ui.screens.features.home.components.CategoriesTitle
import com.example.compose_ui.ui.theme.font_22
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.size_2
import com.example.compose_ui.ui.theme.size_20
import com.example.compose_ui.ui.theme.size_250
import com.example.compose_ui.ui.theme.size_32
import com.example.compose_ui.ui.theme.size_40
import com.example.compose_ui.ui.theme.size_6

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShoesDetail(
    id: String,
    viewModel: ShoesDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBack: () -> Unit
) {
    val shoes by viewModel.shoes.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getShoesDetail(id)
    }
    ContainerPage(
        title = "${shoes?.name.getStringValue()} ${stringResource(id = R.string.detail)}",
        onBackScreen = { onBack() },
        uiState = uiState
    ) {
        Column(
            Modifier
                .weight(6f)
                .verticalScroll(rememberScrollState())
                .padding(size_20)
        ) {
            shoes?.run {
                JPColumn {
                    JPText(
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
                        LazyRow(Modifier.offset {
                            IntOffset(
                                0.dp.roundToPx(),
                                -size_40.roundToPx()
                            )
                        }) {
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
            CategoriesTitle(title = R.string.productRelated, isPadding = false)
            LazyRow {
                items(8) {
                    ProductCard(isLoading = true)
                }
            }
        }
        Row(
            Modifier
                .weight(1.2f)
                .fillMaxWidth()
                .padding(size_32, none),
            verticalAlignment = Alignment.Top,
        ) {
            JPRow(isCenterVer = true) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(21.dp),
                    modifier = Modifier
                        .height(42.dp)
                        .width(42.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD9D9D9)
                    ), contentPadding = PaddingValues(0.dp)
                ) {
                    JPIcon(
                        icon = if (true) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        color = if (true) Color.Red else Color.Black,
                        size = 24.dp
                    )
                }
                JPSpacer(w = size_32)
                JPButton(
                    label = stringResource(id = R.string.addToCart),
                    icon = Icons.Outlined.ShoppingCart,
                    mTop = 0.dp
                ) {

                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ShoesDetailPreview() {
    ShoesDetail("") {

    }
}
