package com.example.compose_ui.ui.cores.data.sources.dataSources.auth

import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.remotes.ApiServices
import com.example.compose_ui.ui.cores.data.remotes.FirebaseServices
import com.example.compose_ui.ui.cores.data.response.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

class AuthDataSourceImpl @Inject constructor()  : IAuthDataSource {
    private val apiServices = FirebaseServices()

    override suspend fun isUserLoggedIn(): ApiResponse<Boolean> = apiServices.isLoggedIn

    override suspend fun login(email: String, password: String): ApiResponse<Person> {
        return apiServices.login(email, password)
    }
}