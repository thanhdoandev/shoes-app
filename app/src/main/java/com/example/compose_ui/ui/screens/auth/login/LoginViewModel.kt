package com.example.compose_ui.ui.screens.auth.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.compose_ui.ui.bases.BaseViewModel
import com.example.compose_ui.ui.bases.UiStateBase
import com.example.compose_ui.ui.cores.data.repository.auth.IAuthRepository
import com.example.compose_ui.ui.utils.validations.EmailValidation
import com.example.compose_ui.ui.utils.validations.PasswordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginInputData(
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
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authRepository: IAuthRepository
) : BaseViewModel(savedStateHandle) {
    private val _loginUiState = MutableStateFlow(LoginInputData())
    private val _isLogged: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val validateEmail = EmailValidation()
    private val validatePassword = PasswordValidation()

    val loginUiState: StateFlow<UiStateBase<LoginInputData>> =
        combine(_loginUiState, _isLogged) { input, isLogin ->
            if (isLogin) UiStateBase.Success else UiStateBase.UiUpdated(data = input)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiStateBase.UiUpdated(LoginInputData())
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
            value.run {
                viewModelScope.launch {
                    callApisOnThread(
                        apis = listOf(authRepository.login(email, password)),
                        onEachSuccess = {
                            _isLogged.value = true
                        }
                    )
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