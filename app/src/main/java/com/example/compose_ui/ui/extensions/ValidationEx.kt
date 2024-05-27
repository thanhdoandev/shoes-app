package com.example.compose_ui.ui.extensions

import android.util.Patterns

fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPasswordValid(): Boolean {
    return this.run {
        any { it.isDigit() } && any { it.isLetter() }
    }
}

fun String.isNumberValid(): Boolean {
    return this.isBlank() || Regex("^\\d+\$").matches(this)
}