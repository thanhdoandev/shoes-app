package com.example.compose_ui.ui.cores.data.repository.products

import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    suspend fun getProducts(): Flow<ApiResponse<MutableList<Product>>>

    suspend fun getProduct(id: String): Flow<ApiResponse<Product>>
}