package com.example.compose_ui.ui.screens.features.home.ui.seach.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.extensions.onClickNoEffect
import com.example.compose_ui.ui.theme.secondaryText
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_6
import com.example.compose_ui.ui.theme.size_8

@Composable
fun HistorySearch(item: String, onClickItem: () -> Unit = {}) {
    JPRow(
        Modifier
            .fillMaxWidth()
            .onClickNoEffect {
                onClickItem()
            }
            .padding(size_16, size_8),
        isCenterVer = true
    ) {
        JPIcon(icon = Icons.Default.History, color = secondaryText)
        JPText(text = item, mStart = size_6, color = secondaryText)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun HistorySearchPreview() {
    HistorySearch("Product search")
}