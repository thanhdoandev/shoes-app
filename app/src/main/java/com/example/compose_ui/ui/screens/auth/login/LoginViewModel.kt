package com.example.compose_ui.ui.screens.auth.login

import androidx.lifecycle.SavedStateHandle
import com.example.compose_ui.ui.components.bases.BaseViewModel
import com.example.compose_ui.ui.utils.validations.EmailValidation
import com.example.compose_ui.ui.utils.validations.PasswordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class LoginData(
    var email: String = "",
    var password: String = "",
    var emailError: Int? = null,
    var passwordError: Int? = null
)

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()

    data object Submit : LoginEvent()
}


@HiltViewModel
class LoginViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    private val _loginSate = MutableStateFlow(LoginData())
    val loginState: StateFlow<LoginData> = _loginSate.asStateFlow()
    private val validateEmail = EmailValidation()
    private val validatePassword = PasswordValidation()


    private val _isLoginSuccess = MutableStateFlow(false)
    val isLoginSuccess = _isLoginSuccess.asStateFlow()

    internal fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _loginSate.value = _loginSate.value.copy(email = event.email)
                isValidateEmail()
            }

            is LoginEvent.PasswordChanged -> {
                _loginSate.value = _loginSate.value.copy(password = event.password)
                isValidatePassword()
            }

            is LoginEvent.Submit -> {
                val emailValid = isValidateEmail()
                val passwordValid = isValidatePassword()
                if (emailValid && passwordValid) {
                    loginState.value.run {
                        loginToServer(email, password) {
                            _isLoginSuccess.value = it
                        }
                    }
                }
            }
        }
    }

    private fun isValidateEmail(): Boolean {
        val emailResult = validateEmail.execute(_loginSate.value.email)
        _loginSate.value = _loginSate.value.copy(emailError = emailResult.errorMessage)
        return emailResult.isSuccess
    }

    private fun isValidatePassword(): Boolean {
        val passValid = validatePassword.execute(_loginSate.value.password)
        _loginSate.value = _loginSate.value.copy(passwordError = passValid.errorMessage)
        return passValid.isSuccess
    }
}