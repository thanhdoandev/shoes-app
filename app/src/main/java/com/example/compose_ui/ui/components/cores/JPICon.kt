package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.cores.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.extensions.onClickNoEffect
import com.example.compose_ui.ui.theme.none

@Composable
fun JPIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    marginAll: Dp = none,
    mTop: Dp = none,
    mEnd: Dp = none,
    mStart: Dp = none,
    mBottom: Dp = none,
    mHoz: Dp = none,
    mVer: Dp = none,
    paddingAll: Dp = none,
    pTop: Dp = none,
    pBottom: Dp = none,
    pStart: Dp = none,
    pEnd: Dp = none,
    pHoz: Dp = none,
    pVer: Dp = none,
    size: Dp = 24.dp,
    color: Color = LocalContentColor.current,
    onClick: () -> Unit = {}
) {
    Icon(
        imageVector = icon,
        contentDescription = "",
        modifier = modifier
            .modifierMargin(
                Margin(
                    marginAll,
                    mTop,
                    mEnd,
                    mStart,
                    mBottom,
                    mHoz,
                    mVer,
                    paddingAll,
                    pTop,
                    pBottom,
                    pStart,
                    pEnd,
                    pHoz,
                    pVer
                )
            )
            .size(size)
            .onClickNoEffect { onClick() },
        tint = color
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun JPIconPreview() {

}