package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(mTop))
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
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                imgButton?.let {
                    Image(
                        modifier = Modifier
                            .size(22.dp),
                        painter = painterResource(id = it),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                JPText(
                    text = label,
                    size = 16.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun JPSecondaryButton() {
    JPSecondaryButton(label = "Button") {}
}