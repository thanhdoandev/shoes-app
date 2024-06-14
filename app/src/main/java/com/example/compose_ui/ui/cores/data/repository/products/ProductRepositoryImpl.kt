package com.example.compose_ui.ui.cores.data.repository.products

import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.sources.dataSources.products.IProductDataSource
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import com.example.compose_ui.ui.extensions.castToMutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(private val productDataSource: IProductDataSource) :
    IProductRepository {
    private val lastProductMutex: Mutex = Mutex()
    private var products: MutableList<Product> = mutableListOf()

    override suspend fun getProducts(): Flow<ApiResponse<MutableList<Product>>> {
        try {
            if (products.isEmpty()) {
                val newProducts: MutableList<Product> =
                    when (val apiResponse = productDataSource.getProducts()) {
                        is ApiResponse.Success -> apiResponse.values
                        else -> mutableListOf()
                    }
                lastProductMutex.withLock {
                    this.products = newProducts.castToMutableList()
                }
            }
            val finalResult = lastProductMutex.withLock { this.products }
            return flow { emit(ApiResponse.Success(finalResult)) }
        } catch (e: Exception) {
            return flow { emit(ApiResponse.Error(e.message.toString())) }
        }
    }

    override suspend fun getProduct(id: String): Flow<ApiResponse<Product>> {
        return flow { emit(productDataSource.getProduct(id)) }
    }
}