package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.compose_ui.ui.cores.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.theme.none

@Composable
fun JPColumn(
    modifier: Modifier = Modifier,
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
    isCenterHoz: Boolean = false,
    isCenterVer: Boolean = false,
    hoz: Alignment.Horizontal = Alignment.Start,
    ver: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit,
) {
    var modifierCustom = modifier
    if (isCenterHoz) modifierCustom = modifier.fillMaxWidth()
    if (isCenterVer) modifierCustom = modifier.fillMaxHeight()
    Column(
        modifier = modifierCustom.modifierMargin(
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
        ),
        horizontalAlignment = if (isCenterHoz) Alignment.CenterHorizontally else hoz,
        verticalArrangement = if (isCenterVer) Arrangement.Center else ver
    ) {
        content()
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun JPColumnPreview() {
    JPColumn {

    }
}