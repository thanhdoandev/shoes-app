package com.example.compose_ui.ui.cores.data.sources.dataSources.categories

import com.example.compose_ui.ui.cores.data.model.Category
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse

interface ICategoryDataSource {
    suspend fun getCategories(): ApiResponse<MutableList<Category>>
}