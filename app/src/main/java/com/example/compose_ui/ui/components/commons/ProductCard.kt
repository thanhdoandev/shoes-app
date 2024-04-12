package com.example.compose_ui.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPLocalImage
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.database.localdata.Products
import com.example.compose_ui.ui.data.vo.Product
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText
import java.util.Random

@Composable
fun ProductCard(
    product: Product,
    onViewDetail: (id: Int) -> Unit,
    onAddToCart: (id: Int) -> Unit,
    isFromFavorite: Boolean = false
) {
    product.run {
        Row {
            Spacer(modifier = Modifier.width(16.dp))
            Card(
                modifier = Modifier
                    .width(180.dp)
                    .padding(1.dp, 1.dp)
                    .clickable {
                        onViewDetail(id)
                    },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                )
            ) {
                Column(modifier = Modifier.padding(16.dp, 0.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    if (isFromFavorite) {
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD9D9D9)
                            ), contentPadding = PaddingValues(0.dp)
                        ) {
                            JPIcon(
                                icon = Icons.Default.Favorite,
                                color = Color.Red,
                                size = 20.dp
                            )
                        }
                    } else {
                        JPIcon(
                            icon = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            color = if (isFavorite) Color.Red else Color.Black,
                            size = 20.dp
                        )
                    }
                    JPLocalImage(url = image, width = 150.dp, height = 120.dp)
                    JPText(text = "BEST SELLER", size = 13.sp, color = primaryColor)
                    JPText(
                        text = name,
                        size = 16.sp,
                        color = primaryText,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.width(180.dp)
                ) {
                    JPText(
                        text = "${price}$",
                        modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
                        isCenter = true,
                        color = Color.Red,
                        fontWeight = FontWeight.Medium,
                        size = 16.sp
                    )
                    if (!isFromFavorite) {
                        Button(
                            onClick = {
                                onAddToCart(id)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primaryColor,
                            ),
                            modifier = Modifier.offset {
                                IntOffset(0.dp.roundToPx(), 4.dp.roundToPx())
                            },
                            shape = RoundedCornerShape(16.dp, 0.dp, 8.dp, 0.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            JPIcon(icon = Icons.Filled.Add, color = Color.White, size = 32.dp)
                        }
                    } else {
                        val rnd = Random();
                        val color =
                            Color(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                        val color1 =
                            Color(10, rnd.nextInt(256), rnd.nextInt(100), rnd.nextInt(156))

                        Row(Modifier.padding(16.dp, 10.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(color, shape = RoundedCornerShape(12.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(color1, shape = RoundedCornerShape(12.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun ProductCardPreview() {
    Products.getOrNull(0)?.let { ProductCard(it, onAddToCart = {}, onViewDetail = {}) }
}