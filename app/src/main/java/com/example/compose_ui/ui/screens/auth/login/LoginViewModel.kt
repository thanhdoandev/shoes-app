package com.example.compose_ui.ui.screens.auth.login

import androidx.lifecycle.SavedStateHandle
import com.example.compose_ui.ui.components.bases.BaseViewModel
import com.example.compose_ui.ui.data.enums.EFieldType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class LoginData(
    var email: String = "",
    var password: String = ""
)

@HiltViewModel
class LoginViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    private val _loginData = MutableStateFlow(LoginData())
    val loginData: StateFlow<LoginData> = _loginData.asStateFlow()

    private val _isLoginSuccess = MutableStateFlow(false)
    val isLoginSuccess = _isLoginSuccess.asStateFlow()

    internal fun onLogin() {
        loginToServer(loginData.value.email, loginData.value.password) {
            _isLoginSuccess.value = it
        }
    }

    internal fun updateLogin(typeField: EFieldType, values: String) {
        _loginData.value = _loginData.value.copy().apply {
            if (typeField == EFieldType.EMAIL) email = values else password = values
        }
    }
}