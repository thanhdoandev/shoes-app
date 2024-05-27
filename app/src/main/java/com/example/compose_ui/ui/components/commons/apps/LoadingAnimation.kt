package com.example.compose_ui.ui.components.commons.apps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.animatedGradient
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.theme.bgLoadingColor
import com.example.compose_ui.ui.theme.none

@Composable
fun LoadingAnimation(
    primaryColor: Color = bgLoadingColor,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    height: Dp = 220.dp,
    width: Dp = 180.dp,
    border: Dp = 16.dp,
    mHoz: Dp = none
) {
    Box(
        modifier = Modifier
            .modifierMargin(Margin(mHoz = mHoz))
            .height(height)
            .width(width)
            .animatedGradient(
                primaryColor = primaryColor,
                containerColor = containerColor,
                border = border
            )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoadingAnimationPreview() {
    LoadingAnimation()
}