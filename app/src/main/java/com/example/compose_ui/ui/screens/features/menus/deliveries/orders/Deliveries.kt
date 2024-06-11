package com.example.compose_ui.ui.screens.features.menus.deliveries.orders

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.cores.data.vo.getProductPreview
import com.example.compose_ui.ui.cores.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.screens.features.menus.deliveries.components.ProductDelivery
import com.example.compose_ui.ui.theme.size_16

@Composable
fun Deliveries(onOpenMap: () -> Unit, onOpenMenu: () -> Unit) {
    DeliveryScreen(onOpenMap = onOpenMap) {
        onOpenMenu()
    }
}

@Composable
private fun DeliveryScreen(onOpenMap: () -> Unit = {}, onOpenMenu: () -> Unit = {}) {
    ContainerPage(
        stringResource(id = R.string.deliverTitle),
        isMenu = true,
        onBackScreen = onOpenMenu
    ) {
        LazyColumn(Modifier.modifierMargin(Margin(mVer = size_16, mHoz = size_16))) {
            item { ProductDelivery(item = getProductPreview(), onClickMapIcon = onOpenMap) }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun DeliveryPreview() {
    DeliveryScreen()
}