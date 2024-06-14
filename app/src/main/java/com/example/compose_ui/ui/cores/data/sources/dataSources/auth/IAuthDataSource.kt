package com.example.compose_ui.ui.cores.data.sources.dataSources.auth

import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse

interface IAuthDataSource {
    suspend fun isUserLoggedIn(): ApiResponse<Boolean>

    suspend fun login(email: String, password: String): ApiResponse<Person>

    suspend fun register(person: Person): ApiResponse<Person>

    suspend fun logout(): ApiResponse<Boolean>
}