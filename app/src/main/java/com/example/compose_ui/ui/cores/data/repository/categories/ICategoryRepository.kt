package com.example.compose_ui.ui.cores.data.repository.categories

import com.example.compose_ui.ui.cores.data.model.Category
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    suspend fun getCategories(): Flow<ApiResponse<MutableList<Category>>>
}