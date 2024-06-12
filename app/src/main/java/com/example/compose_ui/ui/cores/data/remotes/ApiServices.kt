package com.example.compose_ui.ui.cores.data.remotes

import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.response.ApiResponse
import javax.inject.Singleton

@Singleton
class ApiServices {
    private val fireBase = FirebaseServices()

    suspend fun isUserLoggedIn(): ApiResponse<Boolean> = fireBase.isLoggedIn

    suspend fun login(email: String, password: String): ApiResponse<Person> {
        return fireBase.login(email, password)
    }
}