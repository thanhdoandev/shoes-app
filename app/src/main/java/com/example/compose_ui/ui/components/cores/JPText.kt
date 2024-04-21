package com.example.compose_ui.ui.components.cores

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JPText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = null,
    size: TextUnit? = null,
    family: FontFamily? = FontFamily.Default,
    isCenter: Boolean = false,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight? = null,
    lineHeight: TextUnit = 24.sp,
    mTop: Dp = 0.dp,
    mBottom: Dp = 0.dp,
    mStart: Dp = 0.dp,
    mEnd: Dp = 0.dp,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    style: TextStyle = typography.bodyMedium,
) {

    var newStyle = style
    var newModifier = modifier
    if (color != null) {
        newStyle = style.copy(color = color)
    }
    if (size != null) {
        newStyle = style.copy(fontSize = size)
    }
    if (fontWeight != null) {
        newStyle = style.copy(fontWeight = fontWeight)
    }
    if (isCenter) {
        newModifier = modifier.fillMaxWidth()
    }
    Column {
        JPSpacer(h = mTop)
        Row {
            JPSpacer(w = mStart)
            Text(
                style = newStyle,
                modifier = newModifier,
                text = text,
                fontFamily = family,
                lineHeight = lineHeight,
                maxLines = maxLines,
                overflow = overflow,
                textAlign = if (isCenter) TextAlign.Center else textAlign,
            )
            JPSpacer(w = mEnd)
        }
        JPSpacer(h = mBottom)
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun JPTextPreview() {
    JPText("Doan tien Thanh")
}