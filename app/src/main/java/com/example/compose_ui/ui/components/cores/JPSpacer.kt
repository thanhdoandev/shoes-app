package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JPSpacer(h: Dp = 0.dp, w: Dp = 0.dp) {
    Spacer(
        modifier = Modifier
            .width(w)
            .height(h)
    )
}