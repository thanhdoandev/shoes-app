package com.example.compose_ui.ui.screens.features.profile

import com.example.compose_ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : BaseViewModel() {
    private val _isLogoutSuccess = MutableStateFlow(false)
    val isLogoutSuccess = _isLogoutSuccess.asStateFlow()

    internal fun onLogout() {
        userSignOut {
            _isLogoutSuccess.value = true
        }
    }
}