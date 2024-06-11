package com.example.compose_ui.ui.screens.auth.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.compose_ui.ui.components.bases.BaseViewModel
import com.example.compose_ui.ui.components.bases.UiState
import com.example.compose_ui.ui.utils.validations.EmailValidation
import com.example.compose_ui.ui.utils.validations.PasswordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class UiLoginState(
    var email: String = "",
    var password: String = "",
    var emailError: Int? = null,
    var passwordError: Int? = null,
    var loginState: UiState = UiState()
)

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object Submit : LoginEvent()
}

sealed class LoginState {
    data class LoginUi(val loginData: UiLoginState) : LoginState()
    data object Success : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    private val _loginUiState = MutableStateFlow(UiLoginState())
    private val isLogged: MutableStateFlow<Boolean>
        get() = MutableStateFlow(isUserSigned())
    private val validateEmail = EmailValidation()
    private val validatePassword = PasswordValidation()

    val loginUiState: StateFlow<LoginState> =
        combine(_loginUiState, isLogged) { loginInput, isLogin ->
            if (isLogin) LoginState.Success else LoginState.LoginUi(loginData = loginInput)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LoginState.LoginUi(UiLoginState())
        )

    internal fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _loginUiState.value = _loginUiState.value.copy(email = event.email)
                isValidateEmail()
            }

            is LoginEvent.PasswordChanged -> {
                _loginUiState.value = _loginUiState.value.copy(password = event.password)
                isValidatePassword()
            }

            is LoginEvent.Submit -> {
                val emailValid = isValidateEmail()
                val passwordValid = isValidatePassword()
                if (emailValid && passwordValid) {
                    onLogin()
                }
            }
        }
    }

    private fun onLogin() {
        _loginUiState.apply {
            value = value.copy(loginState = UiState(isLoading = true))
            value.run {
                loginToServer(email, password) {
                    isLogged.value = it
                    value = value.copy(loginState = uiState.value)
                }
            }
        }
    }

    private fun isValidateEmail(): Boolean {
        _loginUiState.apply {
            val emailResult = validateEmail.execute(value.email)
            value = value.copy(emailError = emailResult.errorMessage)
            return emailResult.isSuccess
        }
    }

    private fun isValidatePassword(): Boolean {
        _loginUiState.apply {
            val passValid = validatePassword.execute(value.password)
            value = value.copy(passwordError = passValid.errorMessage)
            return passValid.isSuccess
        }
    }
}