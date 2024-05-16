package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.theme.primaryColor

@Composable
fun JPCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 6.dp,
    roundTopStart: Dp = 0.dp,
    roundTopEnd: Dp = 0.dp,
    roundBottomStart: Dp = 0.dp,
    roundBottomEnd: Dp = 0.dp,
    round: Dp? = null,
    w: Dp = 0.dp,
    h: Dp = 0.dp,
    padding: Dp = 16.dp,
    bgColor: Color = primaryColor,
    contentColor: Color = Color.White,
    isMaxSize: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    var newModifier = modifier
    if (w != 0.dp) {
        newModifier = modifier.width(w)
    }
    if (h != 0.dp) {
        newModifier = modifier.height(h)
    }
    if (isMaxSize) {
        newModifier = modifier.fillMaxSize()
    }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
        shape = RoundedCornerShape(
            topStart = round ?: roundTopStart,
            topEnd = round ?: roundTopEnd,
            bottomEnd = round ?: roundBottomEnd,
            bottomStart = round ?: roundBottomStart
        ),
        modifier = newModifier.clickable {
            onClick()
        },
        colors = CardDefaults.cardColors(
            containerColor = bgColor,
            contentColor = contentColor
        )
    ) {
        Column(Modifier.padding(padding)) {
            content()
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun JPCardPreview() {
    JPCard {

    }
}