package com.example.compose_ui.ui.components.bases

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    value: String = "",
    mTop: Dp = 16.dp,
    color: Color = primaryColor,
    txtColor: Color = primaryText,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
    onValueChange: (text: String) -> Unit,
) {
    Column(modifier = modifier
        .wrapContentWidth()
        .clickable { onClick() }) {
        Spacer(modifier = Modifier.height(mTop))
        TextField(
            modifier = modifier
                .height(52.dp)
                .border(border = BorderStroke(1.dp, color), shape = RoundedCornerShape(10)),
            value = value,
            maxLines = 1,
            placeholder = { Text(text = "Looking for the shoes", color = txtColor) },
            leadingIcon = {
                JPIcon(icon = Icons.Default.Search, color = color)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = primaryColor,
                textColor = txtColor,
                disabledLabelColor = txtColor,
                cursorColor = txtColor
            ),
            onValueChange = {
                onValueChange(it)
            },
            singleLine = true,
            enabled = isEnabled
        )
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun SearchInputPreview() {
    SearchInput {

    }
}