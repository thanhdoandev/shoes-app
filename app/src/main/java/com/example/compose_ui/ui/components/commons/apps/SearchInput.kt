package com.example.compose_ui.ui.components.commons.apps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.extensions.onClickNoEffect
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.size_1
import com.example.compose_ui.ui.theme.size_32
import com.example.compose_ui.ui.theme.size_50

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    mTop: Dp = 16.dp,
    borderColor: Color = primaryColor,
    bgColor: Color? = null,
    hint: String = "",
    value: String = "",
    hintColor: Color? = null,
    isEnabled: Boolean = true,
    icon: ImageVector? = null,
    mHoz: Dp = none,
    onClick: () -> Unit = {},
    onClickIcon: () -> Unit = {},
    onValueChange: (text: String) -> Unit,
) {

    val bgSearch = bgColor ?: MaterialTheme.colorScheme.background
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(value) {
        if (value != searchValue) {
            searchValue = value
        }
    }

    JPColumn(
        modifier = modifier.onClickNoEffect { onClick() },
        mHoz = mHoz
    ) {
        Spacer(modifier = Modifier.height(mTop))
        TextField(
            searchValue, {
                searchValue = it
                onValueChange(searchValue)
            },
            modifier
                .fillMaxWidth()
                .height(52.dp)
                .border(border = BorderStroke(1.dp, borderColor), shape = RoundedCornerShape(10)),
            isEnabled,
            maxLines = 1,
            placeholder = {
                JPText(text = hint, color = hintColor)
            },
            leadingIcon = {
                JPIcon(icon = Icons.Default.Search, color = borderColor)
            },
            trailingIcon = {
                if (icon != null) {
                    JPRow(
                        Modifier.width(size_50),
                        isCenterVer = true
                    ) {
                        JPSpacer(
                            modifier
                                .background(borderColor),
                            w = size_1, h = size_32,
                        )
                        JPIcon(
                            icon = icon,
                            modifier = Modifier.fillMaxWidth(),
                            color = borderColor,
                            onClick = { onClickIcon() }
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = bgSearch,
                unfocusedContainerColor = bgSearch,
                unfocusedIndicatorColor = bgSearch,
                focusedIndicatorColor = bgSearch,
                disabledContainerColor = bgSearch
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun SearchInputPreview() {
    SearchInput(hint = "Search ...") {

    }
}