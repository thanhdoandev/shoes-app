package com.example.compose_ui.ui.screens.features.menus.settings.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.extensions.onClickNoEffect
import com.example.compose_ui.ui.screens.features.menus.settings.settings.SettingItem
import com.example.compose_ui.ui.theme.font_16
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.size_20
import com.example.compose_ui.ui.theme.size_28
import com.example.compose_ui.ui.theme.size_6

@Composable
fun SettingItem(
    item: SettingItem,
    index: Int = 0,
    valueEnd: String = "",
    onClick: (router: EScreenName) -> Unit = {}
) {
    item.run {
        JPColumn(Modifier.onClickNoEffect { onClick(route) }) {
            if (index != 0) HorizontalDivider(thickness = 1.dp)
            JPRow(Modifier.fillMaxWidth(), pVer = size_20) {
                JPIcon(icon = icon, size = size_28)
                JPText(
                    text = stringResource(id = title),
                    mStart = size_6,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = font_16,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                if (isTextEnd) JPText(text = valueEnd, color = primaryColor)
                else JPIcon(icon = Icons.AutoMirrored.Filled.KeyboardArrowRight)
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun SettingItemPreview() {
    SettingItem(SettingItem.About)
}