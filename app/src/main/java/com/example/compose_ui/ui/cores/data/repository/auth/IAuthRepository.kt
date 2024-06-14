package com.example.compose_ui.ui.cores.data.repository.auth

import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun isLoggedIn(): Flow<ApiResponse<Boolean>>

    suspend fun login(email: String, password: String): Flow<ApiResponse<Person>>

    suspend fun register(person: Person): Flow<ApiResponse<Person>>

    suspend fun logout(): Flow<ApiResponse<Boolean>>
}