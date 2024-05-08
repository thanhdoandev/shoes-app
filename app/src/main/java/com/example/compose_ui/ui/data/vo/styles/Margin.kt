package com.example.compose_ui.ui.data.vo.styles

import androidx.compose.ui.unit.Dp
import com.example.compose_ui.ui.theme.none

data class Margin(
    val marginAll: Dp = none,
    val mTop: Dp = none,
    val mEnd: Dp = none,
    val mStart: Dp = none,
    val mBottom: Dp = none,
    val mHoz: Dp = none,
    val mVer: Dp = none,
    val paddingAll: Dp = none,
    val pTop: Dp = none,
    val pBottom: Dp = none,
    val pStart: Dp = none,
    val pEnd: Dp = none,
    val pHoz: Dp = none,
    val pVer: Dp = none
)