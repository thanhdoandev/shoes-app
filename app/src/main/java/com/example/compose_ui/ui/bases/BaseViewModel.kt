package com.example.compose_ui.ui.bases

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_ui.ui.cores.data.model.UiState
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
open class BaseViewModel @Inject constructor(val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    open fun <T> callApisOnThread(
        apis: List<Flow<ApiResponse<T>>>,
        onFinish: () -> Unit = {},
        onEachSuccess: (T) -> Unit = {},
        onError: (error: String) -> Unit = {},
        isLoading: Boolean = true,
        isVisibleError: Boolean = true
    ) {
        if (isLoading) {
            _uiState.value = _uiState.value.copy(isLoading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            apis.map { request ->
                async {
                    request.catch { }.collect { result ->
                        withContext(Dispatchers.Main) {
                            handleApiResult(
                                result = result,
                                apiSuccess = onEachSuccess,
                                onError = onError,
                                isError = isVisibleError
                            )
                        }
                    }
                }
            }.awaitAll()
            withContext(Dispatchers.Main) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                onFinish()
            }
        }
    }

    private fun <T> handleApiResult(
        result: ApiResponse<T>,
        apiSuccess: (T) -> Unit,
        onError: (error: String) -> Unit,
        isError: Boolean = true
    ) {
        when (result) {
            is ApiResponse.Success -> {
                apiSuccess(result.values)
            }

            is ApiResponse.Error -> {
                if (isError) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.error
                    )
                }
                onError(result.error)
            }
        }
    }
}