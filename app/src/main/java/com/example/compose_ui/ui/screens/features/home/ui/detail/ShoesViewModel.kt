package com.example.compose_ui.ui.screens.features.home.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.compose_ui.ui.data.database.localdata.Products
import com.example.compose_ui.ui.data.vo.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShoesViewModel : ViewModel() {
    private val _shoes: MutableStateFlow<Product?> = MutableStateFlow(null)
    val shoes: StateFlow<Product?> = _shoes.asStateFlow()

    internal fun getShoesDetail(id: String) {
        Products.firstOrNull { id.toInt() == it.id }?.let { product ->
            _shoes.value = product
        }
    }

    internal fun likeShoes(isLike: Boolean) {
        _shoes.value = _shoes.value.apply { this?.isFavorite = isLike }
    }
}