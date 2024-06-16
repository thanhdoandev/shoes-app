package com.example.compose_ui.ui.screens.features.tabs.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.compose_ui.ui.bases.BaseViewModel
import com.example.compose_ui.ui.cores.data.model.Category
import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.repository.categories.ICategoryRepository
import com.example.compose_ui.ui.cores.data.repository.products.IProductRepository
import com.example.compose_ui.ui.extensions.castToMutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    var categories: MutableList<Category> = mutableListOf(),
    var products: MutableList<Product> = mutableListOf(),
    var isCategoryLoading: Boolean = false,
    var isProductLoading: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: IProductRepository,
    private val categoryRepository: ICategoryRepository
) : BaseViewModel(savedStateHandle) {
    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        callApiFromServer()
    }

    private fun callApiFromServer() {
        viewModelScope.launch {
            homeUiState = homeUiState.copy(isCategoryLoading = true, isProductLoading = true)
            callApisOnThread(
                apis = listOf(
                    productRepository.getProducts(),
                    categoryRepository.getCategories()
                ),
                onEachSuccess = { data ->
                    when (data.firstOrNull()) {
                        is Product -> {
                            homeUiState = homeUiState.copy(
                                isProductLoading = false,
                                products = data.castToMutableList()
                            )
                        }

                        is Category -> {
                            homeUiState = homeUiState.copy(
                                isCategoryLoading = false,
                                categories = data.castToMutableList()
                            )
                        }
                    }
                }, isLoading = false
            )
        }
    }
}