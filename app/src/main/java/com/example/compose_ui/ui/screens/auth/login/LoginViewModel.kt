package com.example.compose_ui.ui.screens.auth.login

import com.example.compose_ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : BaseViewModel() {
    private val _isLoginSuccess = MutableStateFlow(false)
    val isLoginSuccess = _isLoginSuccess.asStateFlow()

    internal fun onLogin(email: String, password: String) {
        loginToServer(email, password) {
            _isLoginSuccess.value = it
        }
    }
}