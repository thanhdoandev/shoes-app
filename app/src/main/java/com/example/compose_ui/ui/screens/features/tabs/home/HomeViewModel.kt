package com.example.compose_ui.ui.screens.features.tabs.home

import androidx.lifecycle.SavedStateHandle
import com.example.compose_ui.ui.components.bases.BaseViewModel
import com.example.compose_ui.ui.data.vo.Category
import com.example.compose_ui.ui.data.vo.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

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

    init {
        callApiFromServer()
    }

    private fun callApiFromServer() {
        _isLoadingCategories.value = true
        _isLoadingProducts.value = true
        getCategories {
            _categories.value = it
            _isLoadingCategories.value = false
        }
        getProducts {
            _products.value = it
            _isLoadingProducts.value = false
        }
    }
}