package com.example.compose_ui.ui.screens.features.favorites

import androidx.lifecycle.ViewModel
import com.example.compose_ui.ui.data.database.localdata.Products
import com.example.compose_ui.ui.data.vo.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoritesViewModel() : ViewModel() {
    private var _favorites: MutableStateFlow<MutableList<Product>> =
        MutableStateFlow(mutableListOf())
    var favorites: StateFlow<MutableList<Product>> = _favorites.asStateFlow()

    internal fun getFavorites() {
        _favorites.update {
            Products.filter { it.isFavorite }.toMutableList()
        }
    }
}