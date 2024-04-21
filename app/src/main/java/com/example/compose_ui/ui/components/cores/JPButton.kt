package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.secondaryText

@Composable
fun JPButton(
    modifier: Modifier = Modifier,
    label: String,
    mTop: Dp = 16.dp,
    bgColor: Color = primaryColor,
    textColor: Color = Color.White,
    isEnable: Boolean = true,
    icon: ImageVector? = null,
    isBorder: Boolean = false,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(mTop))
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            onClick = { onClick() },
            shape = RoundedCornerShape(10),
            enabled = isEnable,
            colors = ButtonDefaults.buttonColors(
                containerColor = bgColor,
                disabledContainerColor = secondaryText
            ),
            border = if (isBorder) BorderStroke(1.dp, Color.Red) else null
        ) {
            if (icon != null) JPIcon(icon = icon, size = 24.dp, color = textColor)
            Spacer(modifier = Modifier.width(10.dp))
            JPText(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun JPButtonPreview() {
    JPButton(label = "Button", icon = Icons.Outlined.ShoppingCart) {}
}