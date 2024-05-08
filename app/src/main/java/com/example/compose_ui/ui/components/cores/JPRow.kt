package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.compose_ui.ui.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.theme.none


@Composable
fun JPRow(
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
    hoz: Arrangement.Horizontal = Arrangement.Start,
    ver: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
) {
    var newModifier = modifier
    if (isCenterHoz) {
        newModifier = modifier.fillMaxWidth()
    }
    Row(
        modifier = newModifier.modifierMargin(
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
        horizontalArrangement = if (isCenterHoz) Arrangement.Center else hoz,
        verticalAlignment = if (isCenterVer) Alignment.CenterVertically else ver
    ) {
        content()
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun JPRowPreview() {
    JPRow {

    }
}