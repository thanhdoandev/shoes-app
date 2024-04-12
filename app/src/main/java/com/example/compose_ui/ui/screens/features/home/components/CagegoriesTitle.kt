package com.example.compose_ui.ui.screens.features.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.components.cores.JPTextButton
import com.example.compose_ui.ui.theme.primaryColor

@Composable
fun CategoriesTitle(title: Int, actionTitle: Int? = null, actionClick: () -> Unit = {}) {
    Row(
        Modifier
            .padding(16.dp, 0.dp)
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        JPText(
            text = stringResource(id = title),
            size = 18.sp,
            fontWeight = FontWeight.Medium,
            family = FontFamily.SansSerif,
            textAlign = TextAlign.Start
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
    CategoriesTitle(R.string.loginTitle) {}
}