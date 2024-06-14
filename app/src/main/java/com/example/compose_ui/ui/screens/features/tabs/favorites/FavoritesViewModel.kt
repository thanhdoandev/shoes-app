package com.example.compose_ui.ui.screens.features.tabs.favorites

import androidx.lifecycle.ViewModel
import com.example.compose_ui.ui.cores.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesViewModel() : ViewModel() {
    private var _favorites: MutableStateFlow<MutableList<Product>> =
        MutableStateFlow(mutableListOf())
    var favorites: StateFlow<MutableList<Product>> = _favorites.asStateFlow()

    internal fun getFavorites() {

    }
}