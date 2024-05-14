package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.compose_ui.R
import com.example.compose_ui.ui.data.vo.styles.Margin
import com.example.compose_ui.ui.extensions.modifierMargin
import com.example.compose_ui.ui.theme.none

@Composable
fun JPLocalImage(
    url: Int,
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
    height: Dp? = null,
    width: Dp? = null,
    size: Dp? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        painter = painterResource(id = url),
        contentScale = contentScale,
        contentDescription = "",
        modifier = when {
            height != null && width != null -> {
                modifier
                    .width(width)
                    .height(height)
            }

            size != null -> modifier.size(size)
            height != null -> modifier.height(height)
            width != null -> modifier.width(width)
            else -> modifier
        }.modifierMargin(
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
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun JPLocalImagePreview() {
    JPLocalImage(R.drawable.product_3)
}