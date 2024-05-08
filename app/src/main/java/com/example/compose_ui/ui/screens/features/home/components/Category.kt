package com.example.compose_ui.ui.screens.features.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.components.commons.LoadingAnimation
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.secondaryText
import com.example.compose_ui.ui.theme.size_1
import com.example.compose_ui.ui.theme.size_110
import com.example.compose_ui.ui.theme.size_42
import com.example.compose_ui.ui.theme.size_8

@Composable
fun Category(
    label: String,
    isActive: Boolean = false,
    isLoading: Boolean = false,
) {
    var isButtonActive by rememberSaveable {
        mutableStateOf(isActive)
    }

    val textColor = if (isButtonActive) Color.White else primaryText
    val bgColor = if (isButtonActive) primaryColor else Color.White

    Row {
        Spacer(modifier = Modifier.width(16.dp))
        if (isLoading) LoadingAnimation(height = size_42, width = size_110, border = size_8)
        else Button(
            modifier = Modifier
                .width(size_110)
                .height(size_42),
            onClick = {
                isButtonActive = !isButtonActive
            },
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(
                containerColor = bgColor,
                disabledContainerColor = secondaryText
            ), border = BorderStroke(size_1, primaryColor)
        ) {
            JPText(
                text = label,
                isCenter = true,
                style = MaterialTheme.typography.titleSmall,
                color = textColor
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun CategoryPreview() {
    Category("All shoes", isActive = true)
}