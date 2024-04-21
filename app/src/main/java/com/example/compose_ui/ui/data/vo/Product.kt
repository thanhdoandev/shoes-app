package com.example.compose_ui.ui.data.vo

data class Product(
    var id: String,
    var name: String,
    var price: Double = 0.0,
    var isFavorite: Boolean = false,
    var isBestSeller: Boolean = false,
    var image: String,
    var type: String,
    var description: String
)
