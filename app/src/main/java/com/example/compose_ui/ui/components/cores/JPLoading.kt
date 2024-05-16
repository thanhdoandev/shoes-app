package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.secondaryText
import com.example.compose_ui.ui.theme.size_32
import com.example.compose_ui.ui.theme.size_4

@Composable
fun Loading(
    color: Color = primaryColor,
    trackColor: Color = secondaryText,
    size: Dp = size_32,
    width: Dp = size_4
) {
    CircularProgressIndicator(
        modifier = Modifier.size(size),
        color = color,
        trackColor = trackColor,
        strokeWidth = width,
        strokeCap = StrokeCap.Round
    )
}