package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun JPRow(
    modifier: Modifier = Modifier,
    mTop: Dp = 0.dp,
    mEnd: Dp = 0.dp,
    mStart: Dp = 0.dp,
    mBottom: Dp = 0.dp,
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
    Column {
        JPSpacer(h = mTop)
        Row(
            modifier = newModifier,
            horizontalArrangement = if (isCenterHoz) Arrangement.Center else hoz,
            verticalAlignment = if (isCenterVer) Alignment.CenterVertically else ver
        ) {
            JPSpacer(w = mStart)
            content()
            JPSpacer(w = mEnd)
        }
        JPSpacer(h = mBottom)
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun JPRowPreview() {
    JPRow {

    }
}