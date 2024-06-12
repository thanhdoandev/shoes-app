package com.example.compose_ui.ui.cores.data.repository.auth

import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.response.ApiResponse
import com.example.compose_ui.ui.cores.data.sources.dataSources.auth.IAuthDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(private val dataSource: IAuthDataSource) :
    IAuthRepository {
    override suspend fun isLoggedIn(): Flow<ApiResponse<Boolean>> {
        return flow {
            emit(dataSource.isUserLoggedIn())
        }
    }

    override suspend fun login(email: String, password: String): Flow<ApiResponse<Person>> {
        return flow {
            emit(dataSource.login(email, password))
        }
    }
}
