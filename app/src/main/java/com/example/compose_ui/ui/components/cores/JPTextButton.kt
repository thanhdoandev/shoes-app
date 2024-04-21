package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    mTop: Dp = 0.dp,
    mBottom: Dp = 0.dp,
    onClick: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(mTop))
        JPText(
            text,
            modifier.clickable {
                onClick()
            },
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
    Spacer(modifier = Modifier.height(mBottom))
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun JPTextButtonPreview() {
    JPTextButton("Button") {
    }
}