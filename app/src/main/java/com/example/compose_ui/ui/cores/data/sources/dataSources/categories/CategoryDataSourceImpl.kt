package com.example.compose_ui.ui.cores.data.sources.dataSources.categories

import com.example.compose_ui.ui.cores.data.model.Category
import com.example.compose_ui.ui.cores.data.sources.remotes.FirebaseServices
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import javax.inject.Inject

class CategoryDataSourceImpl @Inject constructor() : ICategoryDataSource {
    private val apiServices = FirebaseServices()
    override suspend fun getCategories(): ApiResponse<MutableList<Category>> {
        return apiServices.getCategories()
    }
}