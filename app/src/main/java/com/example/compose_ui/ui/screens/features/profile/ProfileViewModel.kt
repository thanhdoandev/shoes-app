package com.example.compose_ui.ui.screens.features.profile

import androidx.lifecycle.SavedStateHandle
import com.example.compose_ui.ui.components.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    private val _isLogoutSuccess = MutableStateFlow(false)
    val isLogoutSuccess = _isLogoutSuccess.asStateFlow()

    internal fun onLogout() {
        userSignOut {
            _isLogoutSuccess.value = true
        }
    }
}