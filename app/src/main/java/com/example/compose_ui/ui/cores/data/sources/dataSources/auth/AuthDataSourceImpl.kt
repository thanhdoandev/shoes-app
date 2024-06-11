package com.example.compose_ui.ui.cores.data.sources.dataSources.auth

import com.example.compose_ui.ui.cores.data.remotes.FirebaseServices

class AuthDataSourceImpl(val api: FirebaseServices) : AuthDataSource {
    override suspend fun isUserLoggedIn() {
        TODO("Not yet implemented")
    }
}