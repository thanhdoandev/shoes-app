package com.example.compose_ui.ui.cores.data.vo

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

fun getProductPreview(): Product = Product(
    id = "1",
    name = "Product Name",
    price = 100.0,
    isFavorite = false,
    isBestSeller = false,
    image = "https://c.static-nike.com/a/images/f_auto,cs_srgb/w_1536,c_limit/g1ljiszo4qhthfpluzbt/123-joyride-cdp-apla-xa-xp.jpg",
    type = "Woman's shoes",
    description = "descroption"
)