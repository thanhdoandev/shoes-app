package com.example.compose_ui.ui.screens.features.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.components.cores.JPTextButton
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryColor

@Composable
fun CategoriesTitle(
    title: Int,
    actionTitle: Int? = null,
    isPadding: Boolean = true,
    actionClick: () -> Unit = {}
) {
    JPRow(
        Modifier
            .padding(if (isPadding) 16.dp else none, none)
            .fillMaxWidth(),
        mTop = 16.dp
    ) {
        JPText(
            text = stringResource(id = title),
            style = typography.titleMedium
        )
        actionTitle?.let {
            JPTextButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = it),
                textAlign = TextAlign.End,
                color = primaryColor,
                fontWeight = FontWeight.Medium
            ) {
                actionClick()
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun CategoriesTitlePreview() {
    CategoriesTitle(R.string.loginTitle, actionTitle = R.string.loginTitle) {}
}