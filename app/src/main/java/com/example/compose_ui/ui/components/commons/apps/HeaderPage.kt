package com.example.compose_ui.ui.components.commons.apps

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.theme.CustomComposeTheme
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.size_20
import com.example.compose_ui.ui.theme.size_6

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderPage(
    title: String,
    modifier: Modifier = Modifier,
    onBackScreen: () -> Unit,
    iconAction: ImageVector? = null,
    isBack: Boolean = true,
    onActionClick: () -> Unit? = {},
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = size_6
        ),
        shape = RoundedCornerShape(none, none, size_20, size_20),
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = CustomComposeTheme.appCustomColors.bgHeader,
            ),
            title = {
                JPText(
                    title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
            },
            navigationIcon = {
                if (isBack) IconButton(onClick = { onBackScreen() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
            actions = {
                iconAction?.let {
                    IconButton(onClick = { onActionClick() }) {
                        JPIcon(
                            icon = Icons.Filled.ArrowBack,
                            size = 32.dp
                        )
                    }
                }
            },
            scrollBehavior = scrollBehavior
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun HeaderPagePreview() {
    HeaderPage("Home Screen", onBackScreen = {})
}