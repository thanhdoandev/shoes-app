package com.example.compose_ui.ui.components.commons.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.theme.font_14
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.secondaryText
import com.example.compose_ui.ui.theme.size_1
import com.example.compose_ui.ui.theme.size_12
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_2
import com.example.compose_ui.ui.theme.size_24
import com.example.compose_ui.ui.theme.size_32
import com.example.compose_ui.ui.theme.size_6
import com.example.compose_ui.ui.theme.size_8
import com.example.compose_ui.R

@Composable
fun ConfirmDialog(
    visible: Boolean = false,
    icon: ImageVector? = Icons.Default.Info,
    title: String = "",
    message: String,
    confirmLabel: String = stringResource(id = R.string.btnLabelYes),
    dismissLabel: String = stringResource(id = R.string.btnLabelNo),
    onDismiss: (() -> Unit)? = null,
    onConfirm: () -> Unit
) {
    var isVisible by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(visible) {
        if (visible != isVisible) isVisible = visible
    }

    fun dismissDialog() {
        isVisible = false
        onDismiss?.let {
            it()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        ConfirmDialogView(
            icon = icon,
            title = title,
            message = message,
            confirmLabel = confirmLabel,
            dismissLabel = dismissLabel,
            onDismiss = { dismissDialog() },
            onConfirm = onConfirm
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmDialogView(
    icon: ImageVector? = null,
    title: String = "",
    message: String,
    confirmLabel: String = "",
    dismissLabel: String = "",
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit
) {
    BasicAlertDialog(onDismissRequest = onDismiss) {
        JPCard(
            isClickAble = false,
            round = size_16,
            bgColor = Color.White,
            contentColor = primaryText
        ) {
            JPColumn(isCenterHoz = true) {
                icon?.let {
                    JPIcon(icon = it, color = Color.Red)
                }
                JPText(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    mTop = size_8
                )
                JPText(text = message, mTop = size_2)
                JPRow(Modifier.fillMaxWidth(), hoz = Arrangement.End, mTop = size_12) {
                    Button(
                        modifier = Modifier
                            .height(size_32)
                            .padding(none),
                        onClick = onDismiss,
                        contentPadding = PaddingValues(size_6, size_2),
                        shape = RoundedCornerShape(10),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            disabledContainerColor = secondaryText
                        ),
                        border = BorderStroke(size_1, Color.Red)
                    ) {
                        JPText(
                            text = dismissLabel,
                            color = primaryText,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = font_14,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                    JPSpacer(w = size_8)
                    Button(
                        modifier = Modifier.height(size_32),
                        onClick = onConfirm,
                        contentPadding = PaddingValues(size_24, size_2),
                        shape = RoundedCornerShape(10),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        border = BorderStroke(size_1, primaryColor)
                    ) {
                        JPText(
                            text = confirmLabel,
                            color = primaryText,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = font_14,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun ConfirmDialogPreview() {
    ConfirmDialogView(
        onDismiss = {},
        message = "Do you want to change language to Vietnamese?",
        title = "Language Change",
        confirmLabel = "OK",
        dismissLabel = "NO"
    ) {}
}