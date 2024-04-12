package com.example.compose_ui.ui.components.bases

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_ui.ui.theme.bgLoadingColor
import com.example.compose_ui.ui.theme.bgPage
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.secondaryText

@Composable
fun ContainerPage(
    isVisibleHeader: Boolean = true,
    title: String = "",
    onBackScreen: () -> Unit = {},
    iconAction: ImageVector? = null,
    onActionClick: () -> Unit = {},
    bgColor: Color = bgPage,
    bgColorHeader: Color = Color.White,
    isScrollPage: Boolean = false,
    isVisibleHeaderLine: Boolean = true,
    isBack: Boolean = true,
    isLoading: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    val modifier: Modifier = Modifier
        .fillMaxSize()
        .background(bgColor)

    modifier.run {
        if (isScrollPage) {
            verticalScroll(rememberScrollState(), true)
        }
        if (isLoading) {
            alpha(0.9f)
            composed {
                clickable(
                    enabled = false,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { },
                )
            }
        }
    }

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (header, container, loading) = createRefs()
        if (isVisibleHeader) {
            HeaderPage(
                title = title,
                onBackScreen = { onBackScreen() },
                iconAction = iconAction,
                onActionClick = { onActionClick() },
                isVisibleHeaderLine = isVisibleHeaderLine,
                bgColor = if (isLoading) bgLoadingColor else bgColorHeader,
                isBack = isBack,
                modifier = Modifier.constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
        Column(
            modifier
                .fillMaxSize()
                .background(if (isLoading) bgLoadingColor else bgColor)
                .constrainAs(container)
                {
                    top.linkTo(header.bottom)
                    start.linkTo(parent.start)
                }) {
            content()
        }
        if (isLoading) {
            Dialog(onDismissRequest = { }) {
                Box(Modifier
                    .constrainAs(loading) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }) {
                    Card(
                        shape = RoundedCornerShape(6.dp),
                        backgroundColor = Color.White,
                        modifier = Modifier.size(100.dp)
                    ) {
                        Column(
                            modifier = Modifier.size(80.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(32.dp),
                                color = primaryColor,
                                backgroundColor = secondaryText,
                                strokeWidth = 4.dp,
                                strokeCap = StrokeCap.Round
                            )
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