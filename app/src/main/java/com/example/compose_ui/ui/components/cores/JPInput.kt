package com.example.compose_ui.ui.components.cores

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.R
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText
import java.util.Locale

@Composable
fun JPInput(
    value: String = "",
    label: String = "",
    placeholder: String? = null,
    maxLines: Int = 1,
    errorMessage: String? = "",
    isError: Boolean = false,
    isPassword: Boolean = false,
    iconInput: ImageVector? = null,
    mTop: Dp = 16.dp,
    onValueChange: (text: String) -> Unit
) {
    var isVisiblePassword by rememberSaveable {
        mutableStateOf(false)
    }

    val error = if (errorMessage.isNullOrBlank()) "$label is required field" else errorMessage
    val hint =
        placeholder ?: stringResource(R.string.placeholderField, label.toLowerCase(Locale.ROOT))
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(mTop))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(0.dp),
            value = value,
            maxLines = maxLines,
            placeholder = { Text(text = hint) },
            label = {
                Text(
                    text = label,
                    color = primaryText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            textStyle = TextStyle(
                color = primaryText,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            ),
            visualTransformation = if (!isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (iconInput != null || isPassword) {
                    if (isPassword) {
                        IconButton(
                            onClick = {
                                isVisiblePassword = !isVisiblePassword
                            }
                        ) {
                            Icon(
                                imageVector = if (isVisiblePassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = ""
                            )
                        }
                    } else {
                        iconInput?.let {
                            Icon(imageVector = it, contentDescription = "")
                        }
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = primaryText,
                textColor = primaryText,
                disabledLabelColor = primaryText,
                cursorColor = primaryText
            ),
            isError = isError,
            onValueChange = {
                onValueChange(it)
            },
        )
        Spacer(modifier = Modifier.height(6.dp))
        if (isError)
            Text(text = error, fontSize = 13.sp, color = Color.Red, fontWeight = FontWeight.Normal)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun JPInputPreview() {
    JPInput {}
}