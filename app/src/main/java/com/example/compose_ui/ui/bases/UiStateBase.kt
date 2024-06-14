package com.example.compose_ui.ui.bases

sealed class UiStateBase<out T> {
    data class UiUpdated<out T>(val data: T) : UiStateBase<T>()

    data object Success : UiStateBase<Nothing>()
}