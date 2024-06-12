package com.example.compose_ui.ui.cores.data.sources.dataSources.auth

import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.response.ApiResponse

interface IAuthDataSource {
    suspend fun isUserLoggedIn(): ApiResponse<Boolean>

    suspend fun login(email: String, password: String): ApiResponse<Person>
}