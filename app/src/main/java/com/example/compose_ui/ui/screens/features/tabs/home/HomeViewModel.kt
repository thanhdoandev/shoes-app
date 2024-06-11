package com.example.compose_ui.ui.screens.features.tabs.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.example.compose_ui.ui.components.bases.BaseViewModel
import com.example.compose_ui.ui.cores.data.vo.Category
import com.example.compose_ui.ui.cores.data.vo.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class CategoryState(
    val isLoading: Boolean = false,
    val categories: MutableList<Category> = mutableListOf()
)

data class ProductState(
    val isLoading: Boolean = false,
    val products: MutableList<Product> = mutableListOf()
)

data class HomeState(
    val categoryState: CategoryState,
    val productSate: ProductState
)

@HiltViewModel
class HomeViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {

    private var _categories = MutableStateFlow<MutableList<Category>>(mutableListOf())
    val categories = _categories.asStateFlow()
    private var _isLoadingCategories = MutableStateFlow(false)
    val isLoadingCategories = _isLoadingCategories.asStateFlow()

    private var _products = MutableStateFlow<MutableList<Product>>(mutableListOf())
    val products = _products.asStateFlow()
    private var _isLoadingProducts = MutableStateFlow(false)
    val isLoadingProducts = _isLoadingProducts.asStateFlow()

    var isLoading by mutableStateOf(false)
        private set

    val demo: String get() = ""

    init {
        callApiFromServer()
    }

    private fun callApiFromServer() {
        Log.i("xxxx++", "vao day")
        _isLoadingCategories.value = true
        _isLoadingProducts.value = true
        isLoading = true
        getCategories {
            _categories.value = it
            _isLoadingCategories.value = false
            isLoading = false
        }
        getProducts {
            _products.value = it
            _isLoadingProducts.value = false
            isLoading = false
        }
    }
}