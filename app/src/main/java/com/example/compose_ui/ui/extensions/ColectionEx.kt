package com.example.compose_ui.ui.extensions

fun <T> Any.castToMutableList(): MutableList<T> = try {
    this as MutableList<T>
} catch (_: Exception) {
    mutableListOf()
}