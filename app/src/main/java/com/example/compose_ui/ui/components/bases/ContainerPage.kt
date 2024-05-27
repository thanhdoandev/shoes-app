package com.example.compose_ui.ui.components.bases

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.components.cores.Loading
import com.example.compose_ui.ui.extensions.onClickNoEffect
import com.example.compose_ui.ui.theme.CustomComposeTheme
import com.example.compose_ui.ui.theme.bgLoadingColor
import com.example.compose_ui.ui.theme.size_8

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContainerPage(
    title: String = "",
    onBackScreen: () -> Unit = {},
    iconAction: ImageVector? = null,
    onActionClick: () -> Unit = {},
    isBack: Boolean = true,
    bgColor: Color? = null,
    isMenu: Boolean = false,
    uiState: UIState = UIState(),
    content: @Composable ColumnScope.() -> Unit,
) {

    var isVisibleDialogError by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(uiState) {
        if (uiState.copy().message.isNotEmpty()) {
            isVisibleDialogError = true
        }
    }
    val focusManager = LocalFocusManager.current
    val isKeyBoarVisible: Boolean = WindowInsets.isImeVisible

    Scaffold(
        modifier = Modifier.onClickNoEffect { if (isKeyBoarVisible) focusManager.clearFocus() },
        topBar = {
            AppTopBar(
                isVisible = title.isNotBlank(),
                title = title,
                onBackScreen = {
                    focusManager.clearFocus()
                    onBackScreen()
                },
                iconAction = iconAction,
                onActionClick = onActionClick,
                isBack = isBack,
                isMenu = isMenu
            )
        },
        containerColor = if (uiState.isLoading) bgLoadingColor else bgColor
            ?: CustomComposeTheme.appCustomColors.bgColor
    ) { padding ->
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val (container, loading) = createRefs()
            JPColumn(
                Modifier
                    .alpha(if (uiState.isLoading) 0.5f else 1.0f)
                    .constrainAs(container) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }) {
                content()
            }
            AnimatedVisibility(
                visible = isVisibleDialogError,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                focusManager.clearFocus()
                Dialog(onDismissRequest = {
                    uiState.apply {
                        isLoading = false
                        message = ""
                    }
                    isVisibleDialogError = false
                }) {
                    JPCard(
                        roundTopStart = 16.dp,
                        roundTopEnd = 16.dp,
                        roundBottomStart = 16.dp,
                        roundBottomEnd = 16.dp,
                        bgColor = Color.White
                    ) {
                        JPColumn(Modifier.padding(24.dp, 32.dp)) {
                            JPText(
                                text = "Error Message!",
                                color = Color.Black,
                                style = MaterialTheme.typography.titleMedium
                            )
                            JPText(
                                text = uiState.message,
                                color = Color.Black,
                                mTop = size_8
                            )
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = uiState.isLoading,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                focusManager.clearFocus()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(enabled = false) { }
                        .constrainAs(loading) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.size(100.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        )
                    ) {
                        Column(
                            modifier = Modifier.size(100.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Loading()
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun ContainerPagePreview() {
    ContainerPage(title = "Container Page") {
    }
}