package com.example.compose_ui.ui.cores.data.sources.dataSources.products

import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse

interface IProductDataSource {
    suspend fun getProducts(): ApiResponse<MutableList<Product>>

    suspend fun getProduct(id: String): ApiResponse<Product>

    suspend fun getSimilarProducts(type: String): ApiResponse<MutableList<Product>>

    suspend fun searchProducts(search: String): ApiResponse<MutableList<Product>>
}