package com.example.compose_ui.ui.cores.data.repository.categories

import com.example.compose_ui.ui.cores.data.model.Category
import com.example.compose_ui.ui.cores.data.sources.dataSources.categories.ICategoryDataSource
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryDataSource: ICategoryDataSource) :
    ICategoryRepository {
    override suspend fun getCategories(): Flow<ApiResponse<MutableList<Category>>> {
        return flow {
            emit(categoryDataSource.getCategories())
        }
    }
}