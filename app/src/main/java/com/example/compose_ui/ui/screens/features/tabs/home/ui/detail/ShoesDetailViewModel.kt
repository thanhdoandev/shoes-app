package com.example.compose_ui.ui.screens.features.tabs.home.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.compose_ui.ui.bases.BaseViewModel
import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.repository.products.IProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductDetailUiState(
    val product: Product? = null,
    val similarProducts: MutableList<Product> = mutableListOf(),
    val isLoading: Boolean = false
)

@HiltViewModel
class ShoesDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: IProductRepository
) : BaseViewModel(savedStateHandle) {
    var productDetailUiState: ProductDetailUiState by mutableStateOf(ProductDetailUiState())
        private set

    init {
        savedStateHandle.get<String>("shoesId")?.let(::getShoesDetail)
    }

    fun getShoesDetail(id: String) {
        viewModelScope.launch {
            callApisOnThread(
                apis = listOf(productRepository.getProduct(id)),
                onEachSuccess = {
                    productDetailUiState = productDetailUiState.copy(product = it)
                }
            )
        }
    }

    private fun getSimilarShoes(product: Product) {
//        _isLoadingSimilar.value = true
//        getSimilarProducts(product.type) {
//            it.remove(product)
//            _similarShoes.value = it
//            _isLoadingSimilar.value = false
//        }
    }

    internal fun likeShoes(isLike: Boolean) {
//        _shoes.value = _shoes.value.apply { this?.isFavorite = isLike }
    }
}