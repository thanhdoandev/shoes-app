package com.example.compose_ui.ui.components.bases

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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderPage(
    title: String,
    modifier: Modifier = Modifier,
    onBackScreen: () -> Unit,
    iconAction: ImageVector? = null,
    isVisibleHeaderLine: Boolean = true,
    bgColor: Color = Color.White,
    isBack: Boolean = true,
    onActionClick: () -> Unit? = {},
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = primaryColor,
        )
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = bgColor,
                titleContentColor = Color.White,
            ),
            title = {
                JPText(
                    title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    size = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            },
            navigationIcon = {
                if (isBack) IconButton(onClick = { onBackScreen() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.Black,
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