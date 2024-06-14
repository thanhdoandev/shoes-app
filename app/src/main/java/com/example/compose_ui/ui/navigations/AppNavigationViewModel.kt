package com.example.compose_ui.ui.navigations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.compose_ui.ui.bases.BaseViewModel
import com.example.compose_ui.ui.cores.data.repository.auth.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppNavigationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authRepository: IAuthRepository
) : BaseViewModel(savedStateHandle) {
    var isSigned: Boolean by mutableStateOf(false)
        private set


    init {
        viewModelScope.launch {
            callApisOnThread(
                apis = listOf(authRepository.isLoggedIn()),
                onEachSuccess = { isSign ->
                    isSigned = isSign
                }
            )
        }
    }

    fun userLogout() {
        viewModelScope.launch {
            callApisOnThread(
                apis = listOf(authRepository.logout()),
                onEachSuccess = { _ ->
                    isSigned = false
                })
        }
    }

    fun userLoginSuccess() {
        isSigned = true
    }
}