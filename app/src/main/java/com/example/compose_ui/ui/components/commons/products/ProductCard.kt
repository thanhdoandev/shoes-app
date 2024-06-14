package com.example.compose_ui.ui.components.commons.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.components.commons.apps.LoadingAnimation
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPImage
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.size_8
import java.util.Random

@Composable
fun ProductCard(
    product: Product? = null,
    onViewDetail: (id: String) -> Unit = {},
    onAddToCart: (id: String) -> Unit = {},
    isFromFavorite: Boolean = false,
    isLoading: Boolean = false,
) {
    if (isLoading) {
        LoadingAnimation(mHoz = size_8)
    } else {
        JPCard(
            modifier = Modifier
                .fillMaxWidth()
                .modifierMargin(Margin(mHoz = size_8, mVer = size_8)),
            onClick = { onViewDetail(product?.id.toString()) },
            bgColor = Color.White,
            contentColor = primaryText,
            round = size_8,
            padding = none
        ) {
            product?.run {
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
                    JPImage(url = image, width = 150.dp, height = 120.dp)
                    JPText(text = "BEST SELLER")
                    JPText(text = name, style = typography.titleMedium)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.width(180.dp)
                ) {
                    JPText(
                        text = "${price}$",
                        modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
                        style = typography.titleMedium.copy(color = Color.Red)
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
                                IntOffset(2.dp.roundToPx(), 4.dp.roundToPx())
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
    ProductCard()
}