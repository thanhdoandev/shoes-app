package com.example.compose_ui.ui.data.vo

import com.example.compose_ui.ui.utils.UiText

data class ValidationResult(
    val isSuccess: Boolean = false,
    val errorMessage: Int? = null
)