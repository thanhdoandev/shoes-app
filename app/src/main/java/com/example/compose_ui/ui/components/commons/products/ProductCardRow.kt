package com.example.compose_ui.ui.components.commons.products

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPImage
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.vo.Product
import com.example.compose_ui.ui.data.vo.getProductPreview
import com.example.compose_ui.ui.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.convertToDoubleDisplay
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.size_100
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_2
import com.example.compose_ui.ui.theme.size_6
import com.example.compose_ui.ui.theme.size_8

@Composable
fun ProductCardRow(product: Product, onClick: (id: String) -> Unit) {
    JPCard(
        modifier = Modifier
            .fillMaxWidth()
            .modifierMargin(Margin(mHoz = size_16, mVer = size_8)),
        onClick = { onClick(product.id) },
        bgColor = Color.White,
        contentColor = primaryText,
        round = size_8,
        padding = size_8
    ) {
        JPRow(Modifier.height(size_100)) {
            JPColumn(Modifier.weight(1f), isCenterHoz = true) {
                JPImage(url = product.image, Modifier.fillMaxSize())
            }
            JPColumn(Modifier.weight(2f), pStart = size_6) {
                JPText(
                    text = product.name,
                    style = typography.titleMedium.copy(fontSize = 18.sp)
                )
                JPText(
                    text = product.price.convertToDoubleDisplay(isCurrency = true),
                    fontWeight = FontWeight.Medium,
                    style = typography.bodyMedium.copy(color = Color.Red)
                )
                JPText(text = product.description, mTop = size_2)
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ProductCardRowPreview() {
    ProductCardRow(getProductPreview()) {}
}