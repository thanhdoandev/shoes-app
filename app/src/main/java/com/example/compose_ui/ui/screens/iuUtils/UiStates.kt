package com.example.compose_ui.ui.screens.iuUtils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import com.example.compose_ui.ui.extensions.Keyboard
import com.example.compose_ui.ui.extensions.keyboardAsState

@Stable
class UiStates {
    private val keyBoardSate: State<Keyboard>
        @Composable get() = keyboardAsState()

    val isKeyBoardHide: Boolean
        @Composable get() = keyBoardSate.value == Keyboard.Closed

    val isKeyBoardVisible: Boolean
        @Composable get() = keyBoardSate.value == Keyboard.Opened
}