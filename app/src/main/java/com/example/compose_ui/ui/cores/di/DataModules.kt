package com.example.compose_ui.ui.cores.di

import com.example.compose_ui.ui.cores.data.repository.auth.AuthRepositoryImpl
import com.example.compose_ui.ui.cores.data.repository.auth.IAuthRepository
import com.example.compose_ui.ui.cores.data.sources.dataSources.auth.AuthDataSourceImpl
import com.example.compose_ui.ui.cores.data.sources.dataSources.auth.IAuthDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindTaskRepository(repository: AuthRepositoryImpl): IAuthRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindNetworkAuDataSource(dataSource: AuthDataSourceImpl): IAuthDataSource
}
