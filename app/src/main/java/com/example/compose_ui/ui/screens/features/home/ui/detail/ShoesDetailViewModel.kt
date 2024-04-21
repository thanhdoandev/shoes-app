package com.example.compose_ui.ui.screens.features.home.ui.detail

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

    internal fun getShoesDetail(id: String) {
        getProduct(id) {
            _shoes.value = it
        }
    }

    internal fun likeShoes(isLike: Boolean) {
        _shoes.value = _shoes.value.apply { this?.isFavorite = isLike }
    }
}