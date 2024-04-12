package com.example.compose_ui.ui.screens.features.home.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.cores.JPButton
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPLocalImage
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.database.localdata.Products

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShoesDetail(
    id: String,
    viewModel: ShoesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBack: () -> Unit
) {
    val shoesDetail by viewModel.shoes.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getShoesDetail(id)
    }

    ContainerPage(title = "${shoesDetail?.name} Detail", onBackScreen = { onBack() }) {
        shoesDetail?.run {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.weight(7f)) {
                    JPText(
                        mTop = 32.dp,
                        text = name,
                        size = 32.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 32.sp
                    )
                    JPText(text = type, mTop = 4.dp)
                    JPText(
                        text = "$$price",
                        mTop = 6.dp,
                        size = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    JPLocalImage(url = image, height = 300.dp, width = 400.dp)
                    LazyRow(content = {
                        items(Products) {
                            Card(
                                Modifier
                                    .padding(8.dp)
                                    .clickable { viewModel.getShoesDetail(it.id.toString()) },
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                ),
                            ) {
                                JPLocalImage(url = it.image, size = 100.dp)
                            }
                        }
                    })
                    JPText(text = description)
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(40.dp, 0.dp)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = { viewModel.likeShoes(!isFavorite) },
                                shape = RoundedCornerShape(21.dp),
                                modifier = Modifier
                                    .height(42.dp)
                                    .width(42.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFD9D9D9)
                                ), contentPadding = PaddingValues(0.dp)
                            ) {
                                JPIcon(
                                    icon = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                                    color = if (isFavorite) Color.Red else Color.Black,
                                    size = 24.dp
                                )
                            }
                        }
                        JPButton(
                            label = "Add To Card",
                            icon = Icons.Outlined.ShoppingCart,
                            mTop = 0.dp
                        ) {

                        }
                    }
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
