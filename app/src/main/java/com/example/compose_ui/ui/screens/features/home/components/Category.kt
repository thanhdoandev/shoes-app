package com.example.compose_ui.ui.screens.features.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.secondaryText

@Composable
fun Category(label: String, isActive: Boolean = false) {
    var isButtonActive by rememberSaveable {
        mutableStateOf(isActive)
    }

    val textColor = if (isButtonActive) Color.White else primaryText
    val bgColor = if (isButtonActive) primaryColor else Color.White

    Row {
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            modifier = Modifier
                .width(110.dp)
                .height(42.dp),
            onClick = {
                isButtonActive = !isButtonActive
            },
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(
                containerColor = bgColor,
                disabledContainerColor = secondaryText
            ), border = BorderStroke(0.5.dp, primaryColor)
        ) {
            JPText(
                text = label,
                size = 14.sp,
                color = textColor,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                isCenter = true
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun CategoryPreview() {
    Category("All shoes", isActive = true)
}