package com.example.compose_ui.ui.cores.data.sources.dataSources.auth

interface AuthDataSource {
    suspend fun isUserLoggedIn()
}