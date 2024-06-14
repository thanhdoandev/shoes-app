package com.example.compose_ui.ui.cores.data.repository.auth

import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.sources.dataSources.auth.IAuthDataSource
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(private val dataSource: IAuthDataSource) :
    IAuthRepository {
    private val authLatestMutex: Mutex = Mutex()
    private var isSigned: Boolean = false

    override suspend fun isLoggedIn(): Flow<ApiResponse<Boolean>> {
        if (!isSigned) {
            val isSignedNew = when (val apiResponse = dataSource.isUserLoggedIn()) {
                is ApiResponse.Success -> apiResponse.values
                else -> false
            }
            authLatestMutex.withLock {
                this.isSigned = isSignedNew
            }
        }

        val result = authLatestMutex.withLock { this.isSigned }

        return flow {
            emit(ApiResponse.Success(result))
        }
    }

    override suspend fun login(email: String, password: String): Flow<ApiResponse<Person>> {
        return flow {
            emit(dataSource.login(email, password))
        }
    }

    override suspend fun register(person: Person): Flow<ApiResponse<Person>> {
        return flow {
            emit(dataSource.register(person))
        }
    }

    override suspend fun logout(): Flow<ApiResponse<Boolean>> {
        return flow {
            emit(dataSource.logout())
        }
    }
}
