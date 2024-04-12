package com.example.compose_ui.ui.data.vo

data class Product(
    val id: Int,
    val name: String,
    val price: Double = 0.0,
    var isFavorite: Boolean = false,
    var isBestSeller: Boolean = false,
    var image: Int,
    val type: String,
    val description: String
)
