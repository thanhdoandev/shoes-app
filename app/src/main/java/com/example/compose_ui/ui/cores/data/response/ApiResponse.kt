package com.example.compose_ui.ui.cores.data.response

sealed class ApiResponse<out T> {
    data class Success<out T>(val values: T) : ApiResponse<T>()
    data class Error(val error: String) : ApiResponse<Nothing>()
}