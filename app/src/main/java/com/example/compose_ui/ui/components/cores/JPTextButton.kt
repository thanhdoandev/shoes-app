package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.compose_ui.ui.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryText

@Composable
fun JPTextButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = primaryText,
    size: TextUnit = 16.sp,
    family: FontFamily? = FontFamily.Default,
    isCenter: Boolean = false,
    textAlign: TextAlign = TextAlign.End,
    fontWeight: FontWeight? = FontWeight.Medium,
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
    onClick: () -> Unit
) {
    JPText(
        text,
        modifier
            .clickable {
                onClick()
            }
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
            ),
        style = typography.titleMedium.copy(
            color = color,
            fontSize = size,
            fontFamily = family,
            textAlign = textAlign,
            lineHeight = lineHeight
        ),
        isCenter = isCenter,
        fontWeight = fontWeight,

        )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun JPTextButtonPreview() {
    JPTextButton("Button") {
    }
}