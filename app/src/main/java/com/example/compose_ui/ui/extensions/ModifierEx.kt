package com.example.compose_ui.ui.extensions

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.cores.data.vo.styles.Margin
import com.example.compose_ui.ui.theme.none

fun Modifier.modifierMargin(marginLayout: Margin): Modifier {
    val modifier: Modifier
    marginLayout.run {
        when {
            marginAll != none || marginAll != none -> {
                modifier = this@modifierMargin
                    .padding(marginAll)
                    .padding(marginAll)
            }

            mHoz != none || mVer != none || pHoz != none || pVer != none -> {
                modifier = this@modifierMargin
                    .padding(mHoz, mVer)
                    .padding(pHoz, pVer)
            }

            else -> {
                modifier = this@modifierMargin
                    .padding(mStart, mTop, mEnd, mBottom)
                    .padding(pStart, pTop, pEnd, pBottom)
            }
        }
    }
    return modifier
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.onClickNoEffect(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Composable
fun Modifier.animatedGradient(
    primaryColor: androidx.compose.ui.graphics.Color,
    containerColor: androidx.compose.ui.graphics.Color,
    border: Dp = 16.dp
): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "")
    val colors = listOf(
        primaryColor,
        containerColor,
        primaryColor
    )
    val offsetXAnimation by transition.animateFloat(
        initialValue = -size.width.toFloat(),
        targetValue = size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "gradientAnimation"
    )
    background(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(x = offsetXAnimation, y = 0f),
            end = Offset(x = offsetXAnimation + size.width.toFloat(), y = size.height.toFloat())
        ),
        shape = RoundedCornerShape(border)
    ).onGloballyPositioned {
        size = it.size
    }
}
