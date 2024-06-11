package com.example.compose_ui.ui.screens.features.tabs.home.ui.seach

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.compose_ui.ui.components.bases.BaseViewModel
import com.example.compose_ui.ui.cores.data.vo.Product
import com.example.compose_ui.ui.utils.Constants.Default.DELAY_SEARCH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {

    companion object {
        val HISTORY: MutableList<String> =
            mutableListOf("Shoes product", "Nike jordan 1", "Adidas feature one", "Nike Marcural")
    }

    private val _shoesList = MutableStateFlow<MutableList<Product>>(mutableListOf())
    private val histories: MutableList<String> = HISTORY

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    @OptIn(FlowPreview::class)
    val shoesList: StateFlow<MutableList<Product>> = searchValue.debounce(DELAY_SEARCH)
        .combine(_shoesList) { textSearch, products ->
            if (textSearch.isEmpty()) mutableListOf() else {
                val newProducts: MutableList<Product> = products
                _isLoading.value = true
                searchProducts(textSearch) {
                    newProducts.run {
                        clear()
                        addAll(it)
                        if (!histories.contains(textSearch)) {
                            histories.add(0, textSearch)
                        }
                    }
                    _isLoading.value = false
                }
                newProducts
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _shoesList.value)

    fun searchShoes(value: String) {
        _searchValue.value = value
    }

    fun getHistories() = histories
}