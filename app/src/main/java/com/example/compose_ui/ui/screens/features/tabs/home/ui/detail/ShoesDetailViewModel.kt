package com.example.compose_ui.ui.screens.features.tabs.home.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.example.compose_ui.ui.components.bases.BaseViewModel
import com.example.compose_ui.ui.data.vo.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShoesDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    private val _shoes: MutableStateFlow<Product?> = MutableStateFlow(null)
    val shoes: StateFlow<Product?> = _shoes.asStateFlow()
    private var _similarShoes: MutableStateFlow<MutableList<Product>> =
        MutableStateFlow(mutableListOf())
    val similarShoes: StateFlow<MutableList<Product>> = _similarShoes.asStateFlow()
    private val _isLoadingSimilar = MutableStateFlow(false)
    val isLoadingSimilar = _isLoadingSimilar.asStateFlow()

    init {
        savedStateHandle.get<String>("shoesId")?.let(::getShoesDetail)
    }

    fun getShoesDetail(id: String) {
        getProduct(id) {
            _shoes.value = it
            getSimilarShoes(it)
        }
    }

    private fun getSimilarShoes(product: Product) {
        _isLoadingSimilar.value = true
        getSimilarProducts(product.type) {
            it.remove(product)
            _similarShoes.value = it
            _isLoadingSimilar.value = false
        }
    }

    internal fun likeShoes(isLike: Boolean) {
        _shoes.value = _shoes.value.apply { this?.isFavorite = isLike }
    }
}