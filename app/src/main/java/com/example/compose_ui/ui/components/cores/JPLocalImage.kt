package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose_ui.R

@Composable
fun JPLocalImage(
    url: Int,
    modifier: Modifier = Modifier,
    mTop: Dp = 0.dp,
    mStart: Dp = 0.dp,
    mEnd: Dp = 0.dp,
    height: Dp? = null,
    width: Dp? = null,
    size: Dp? = null
) {
    Column {
        Spacer(modifier = Modifier.height(mTop))
        Row {
            Spacer(modifier = Modifier.width(mStart))
            Image(
                painter = painterResource(id = url),
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
                }
            )
            Spacer(modifier = Modifier.width(mEnd))
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun JPLocalImagePreview() {
    JPLocalImage(R.drawable.product_3)
}