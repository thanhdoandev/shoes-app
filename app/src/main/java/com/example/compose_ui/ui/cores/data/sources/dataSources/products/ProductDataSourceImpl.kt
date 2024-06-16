package com.example.compose_ui.ui.cores.data.sources.dataSources.products

import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.sources.remotes.FirebaseServices
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor() : IProductDataSource {
    private val apiServices = FirebaseServices()

    override suspend fun getProducts(): ApiResponse<MutableList<Product>> {
        return apiServices.getProducts()
    }

    override suspend fun getProduct(id: String): ApiResponse<Product> {
        return apiServices.getProduct(id)
    }

    override suspend fun getSimilarProducts(type: String): ApiResponse<MutableList<Product>> {
        return apiServices.getSimilarProducts(type)
    }

    override suspend fun searchProducts(search: String): ApiResponse<MutableList<Product>> {
        return apiServices.searchProducts(search)
    }
}