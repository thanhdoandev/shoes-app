package com.example.compose_ui.ui.screens.features.menus.deliveries.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.vo.Product
import com.example.compose_ui.ui.data.vo.getProductPreview
import com.example.compose_ui.ui.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.convertToDoubleDisplay
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.extensions.onClickNoEffect
import com.example.compose_ui.ui.theme.font_16
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.size_1
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_2
import com.example.compose_ui.ui.theme.size_32

@Composable
fun ProductDelivery(item: Product, onClickMapIcon: () -> Unit = {}, onClickItem: () -> Unit = {}) {
    item.apply {
        JPCard(
            modifier = Modifier
                .fillMaxWidth()
                .modifierMargin(Margin(mHoz = size_1, mVer = size_1))
                .onClickNoEffect {
                    onClickItem()
                },
            bgColor = Color.White,
            contentColor = primaryText,
            round = size_16,
            padding = none
        ) {
            JPColumn(Modifier.padding(size_16)) {
                JPRow(isCenterVer = true) {
                    JPText(
                        text = "#1111",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium
                    )
                    JPIcon(
                        icon = Icons.Default.Map,
                        color = Color.Red,
                        size = size_32,
                        onClick = onClickMapIcon
                    )
                }
                JPText(text = name)
                JPText(
                    text = stringResource(
                        id = R.string.deliverPriceLabel,
                        price.convertToDoubleDisplay(isCurrency = true)
                    )
                )
            }
            JPCard(
                bgColor = Color.Red,
                contentColor = Color.White,
                padding = none
            ) {
                JPText(
                    text = "Cancelled",
                    isCenter = true,
                    mVer = size_2,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = font_16
                    )
                )
            }
            JPSpacer(h = size_16)
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun ProductDeliveryPreview() {
    ProductDelivery(getProductPreview())
}