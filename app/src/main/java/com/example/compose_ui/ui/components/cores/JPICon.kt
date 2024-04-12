package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JPIcon(
    icon: ImageVector,
    mTop: Dp = 0.dp,
    size: Dp = 24.dp,
    color: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Column {
        Spacer(modifier = Modifier.height(mTop))
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier
                .size(size)
                .clickable {
                    onClick()
                },
            tint = color
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun JPIconPreview() {

}