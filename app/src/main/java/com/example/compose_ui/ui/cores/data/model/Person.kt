package com.example.compose_ui.ui.cores.data.model

data class Person(
    val email: String,
    val password: String,
    var name: String? = null,
    val avatar: String? = null
)