package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose_ui.R
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.secondaryText

@Composable
fun JPSecondaryButton(
    modifier: Modifier = Modifier,
    label: String,
    mTop: Dp = 16.dp,
    bgColor: Color = Color.White,
    textColor: Color = Color.Black,
    isEnable: Boolean = true,
    imgButton: Int? = null,
    onClick: () -> Unit
) {
    JPRow(mTop = mTop) {
        OutlinedButton(
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
            border = BorderStroke(0.5.dp, primaryColor)
        ) {
            JPRow {
                imgButton?.let {
                    JPLocalImage(url = imgButton, size = 22.dp)
                }
                JPSpacer(w = 10.dp)
                JPText(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun JPSecondaryButton() {
    JPSecondaryButton(label = "Button", imgButton = R.drawable.ic_google) {}
}