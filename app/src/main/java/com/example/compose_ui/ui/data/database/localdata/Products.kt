package com.example.compose_ui.ui.data.database.localdata

import com.example.compose_ui.R
import com.example.compose_ui.ui.data.vo.Product

val Products = listOf(
    Product(
        id = 1,
        name = "Nike Jordan",
        price = 302.00,
        isFavorite = false,
        isBestSeller = true,
        image = R.drawable.product_1,
        type = "Men's Shoes",
        description = "The Max Air 270 unit delivers unrivaled, all-day comfort. The sleek, running-inspired design roots you to everything Nike........"
    ),
    Product(
        id = 2,
        name = "Nike Air Max",
        price = 752.00,
        isFavorite = true,
        isBestSeller = true,
        image = R.drawable.img_product_3,
        type = "Woman's Shoes",
        description = "The Max Air 270 unit delivers unrivaled, all-day comfort. The sleek, running-inspired design roots you to everything Nike........"
    ),
    Product(
        id = 3,
        name = "Nike Club Max",
        price = 47.70,
        isFavorite = true,
        isBestSeller = true,
        image = R.drawable.product_2,
        type = "Woman's Shoes",
        description = "The Max Air 270 unit delivers unrivaled, all-day comfort. The sleek, running-inspired design roots you to everything Nike........"
    ),
    Product(
        id = 4,
        name = "Nike Air Max 200",
        price = 192.00,
        isFavorite = true,
        isBestSeller = true,
        image = R.drawable.product_4,
        type = "Men's Shoes",
        description = "The Max Air 270 unit delivers unrivaled, all-day comfort. The sleek, running-inspired design roots you to everything Nike........"
    ),
    Product(
        id = 5,
        name = "Nike Air Max 270 Essential",
        price = 814.00,
        isFavorite = false,
        isBestSeller = false,
        image = R.drawable.product_3,
        type = "Woman's Shoes",
        description = "The Max Air 270 unit delivers unrivaled, all-day comfort. The sleek, running-inspired design roots you to everything Nike........"
    )
)