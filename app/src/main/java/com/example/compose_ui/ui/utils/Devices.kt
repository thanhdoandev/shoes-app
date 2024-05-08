package com.example.compose_ui.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DeviceSize(val width: Int, val height: Int)

@Composable
fun getDeviceSize(): DeviceSize {
    val configuration = LocalConfiguration.current
    return DeviceSize(
        width = configuration.screenWidthDp,
        height = configuration.screenHeightDp
    )
}
