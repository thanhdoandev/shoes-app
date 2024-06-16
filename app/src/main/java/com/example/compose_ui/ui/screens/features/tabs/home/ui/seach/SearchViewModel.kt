package com.example.compose_ui.ui.screens.features.tabs.home.ui.seach

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.compose_ui.ui.bases.BaseViewModel
import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.repository.products.IProductRepository
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
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    var histories: MutableList<String> = mutableListOf(),
    var products: MutableList<Product> = mutableListOf()
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: IProductRepository
) : BaseViewModel(savedStateHandle) {

    companion object {
        val HISTORY: MutableList<String> =
            mutableListOf("Shoes product", "Nike jordan 1", "Adidas feature one", "Nike Marcural")
    }

    private val _searchUiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    var isLoading: Boolean by mutableStateOf(false)
        private set

    @OptIn(FlowPreview::class)
    val searchUiState: StateFlow<SearchUiState> = searchText.debounce(DELAY_SEARCH)
        .combine(_searchUiState.asStateFlow()) { text, products ->
            if (text.isEmpty()) {
                SearchUiState(
                    histories = HISTORY,
                    products = mutableListOf()
                )
            } else {
                val newProducts: MutableList<Product> = products.products
                isLoading = true
                viewModelScope.launch {
                    callApisOnThread(
                        apis = listOf(productRepository.searchProducts(text)),
                        onEachSuccess = {
                            newProducts.apply {
                                clear()
                                addAll(it)
                            }
                            isLoading = false
                        })
                }
                SearchUiState(
                    products = newProducts,
                    histories = mutableListOf()
                )
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), SearchUiState())

    fun searchShoes(value: String) {
        _searchText.value = value
    }
}