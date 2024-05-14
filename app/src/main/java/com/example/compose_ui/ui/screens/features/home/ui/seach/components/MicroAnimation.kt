package com.example.compose_ui.ui.screens.features.home.ui.seach.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.size_50
import com.example.compose_ui.ui.theme.size_80
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MicroAnimation(onInputting: (isInputting: Boolean) -> Unit) {
    val waves = listOf(
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) },
    )
    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(4000, easing = FastOutLinearInEasing),
        repeatMode = RepeatMode.Restart,
    )
    waves.forEachIndexed { index, animatable ->
        LaunchedEffect(animatable) {
            delay(index * 1000L)
            animatable.animateTo(
                targetValue = 1f, animationSpec = animationSpec
            )
        }
    }
    val dys = waves.map { it.value }
    var isInputting by rememberSaveable {
        mutableStateOf<Boolean?>(null)
    }

    LaunchedEffect(Unit) {
        isInputting?.let(onInputting)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isInputting == true) {
            dys.forEach { dy ->
                Box(
                    Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            scaleX = dy * 4 + 1
                            scaleY = dy * 4 + 1
                            alpha = 1 - dy
                        },
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color = primaryColor, shape = CircleShape)
                    )
                }
            }
        }
        Box(
            Modifier
                .size(size_80)
                .align(Alignment.Center)
                .background(color = Color.White, shape = CircleShape)
        ) {
            JPIcon(
                icon = Icons.Default.Mic,
                modifier = Modifier.align(Alignment.Center),
                size = size_50,
                color = primaryColor
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun MicroAnimationPreview() {
    MicroAnimation {

    }
}