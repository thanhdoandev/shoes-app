package com.example.compose_ui.ui.cores.data.sources.dataSources.auth

import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.sources.remotes.FirebaseServices
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor() : IAuthDataSource {
    private val apiServices = FirebaseServices()

    override suspend fun isUserLoggedIn(): ApiResponse<Boolean> = apiServices.isLoggedIn

    override suspend fun login(email: String, password: String): ApiResponse<Person> {
        return apiServices.login(email, password)
    }

    override suspend fun register(person: Person): ApiResponse<Person> {
        return apiServices.register(person)
    }

    override suspend fun logout(): ApiResponse<Boolean> {
        return apiServices.logout()
    }
}