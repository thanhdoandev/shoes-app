package com.example.compose_ui.ui.components.cores

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import com.example.compose_ui.ui.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.theme.none

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
    Text(
        style = newStyle,
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
        text = text,
        fontFamily = family,
        lineHeight = lineHeight,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = if (isCenter) TextAlign.Center else textAlign,
    )

}

@Composable
@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun JPTextPreview() {
    JPText("Doan tien Thanh")
}