package com.example.compose_ui.ui.cores.data.sources.dataSources.products

import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse

interface IProductDataSource {
    suspend fun getProducts(): ApiResponse<MutableList<Product>>

    suspend fun getProduct(id: String): ApiResponse<Product>
}