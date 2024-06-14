package com.example.compose_ui.ui.cores.di

import com.example.compose_ui.ui.cores.data.repository.auth.AuthRepositoryImpl
import com.example.compose_ui.ui.cores.data.repository.auth.IAuthRepository
import com.example.compose_ui.ui.cores.data.repository.categories.CategoryRepositoryImpl
import com.example.compose_ui.ui.cores.data.repository.categories.ICategoryRepository
import com.example.compose_ui.ui.cores.data.repository.products.IProductRepository
import com.example.compose_ui.ui.cores.data.repository.products.ProductRepositoryImpl
import com.example.compose_ui.ui.cores.data.sources.dataSources.auth.AuthDataSourceImpl
import com.example.compose_ui.ui.cores.data.sources.dataSources.auth.IAuthDataSource
import com.example.compose_ui.ui.cores.data.sources.dataSources.categories.CategoryDataSourceImpl
import com.example.compose_ui.ui.cores.data.sources.dataSources.categories.ICategoryDataSource
import com.example.compose_ui.ui.cores.data.sources.dataSources.products.IProductDataSource
import com.example.compose_ui.ui.cores.data.sources.dataSources.products.ProductDataSourceImpl
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
    abstract fun bindAuthRepository(repositoryAuth: AuthRepositoryImpl): IAuthRepository

    @Singleton
    @Binds
    abstract fun bindProductRepository(repositoryProduct: ProductRepositoryImpl): IProductRepository

    @Singleton
    @Binds
    abstract fun bindCategoryRepository(repositoryCategory: CategoryRepositoryImpl): ICategoryRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindNetworkAuthDataSource(dataSourceAuth: AuthDataSourceImpl): IAuthDataSource

    @Singleton
    @Binds
    abstract fun bindNetworkProductDataSource(dataSourceProduct: ProductDataSourceImpl): IProductDataSource

    @Singleton
    @Binds
    abstract fun bindNetworkCategoryDataSource(dataSourceCategory: CategoryDataSourceImpl): ICategoryDataSource
}
