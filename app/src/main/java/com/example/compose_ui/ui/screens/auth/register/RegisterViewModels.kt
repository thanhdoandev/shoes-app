package com.example.compose_ui.ui.screens.auth.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.compose_ui.ui.bases.BaseViewModel
import com.example.compose_ui.ui.cores.data.enums.EFieldType
import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.repository.auth.IAuthRepository
import com.example.compose_ui.ui.utils.validations.EmailValidation
import com.example.compose_ui.ui.utils.validations.FieldValidation
import com.example.compose_ui.ui.utils.validations.PasswordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterData(
    var fullName: String = "",
    var email: String = "",
    var password: String = "",
    var fullNameError: Int? = null,
    var emailError: Int? = null,
    var passwordError: Int? = null,
    var isRegisterSuccess: Boolean = false
)

sealed class RegisterEvents {
    data class InputChanged(val type: EFieldType, val value: String) : RegisterEvents()

    data object Submit : RegisterEvents()
}

@HiltViewModel
class RegisterViewModels @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authRepository: IAuthRepository
) :
    BaseViewModel(savedStateHandle) {
    var uiRegisterState: RegisterData by mutableStateOf(RegisterData())
        private set

    internal fun onRegisterEvent(event: RegisterEvents) {
        when (event) {
            is RegisterEvents.InputChanged -> {
                inputValueChanged(event.type, event.value)
            }

            is RegisterEvents.Submit -> {
                Log.i("xxxx++++", "vao day")
                val isFullNameValid = isValidFullName()
                val isPasswordValid = isValidatePassword()
                val isEmailValid = isValidateEmail()
                Log.i("xxxx++++", "$isEmailValid $isPasswordValid $isFullNameValid")
                if (isEmailValid && isPasswordValid && isFullNameValid) {
                    register()
                }
            }
        }
    }

    private fun inputValueChanged(type: EFieldType, value: String) {
        when (type) {
            EFieldType.YOUR_NAME -> {
                uiRegisterState = uiRegisterState.copy(fullName = value)
                isValidFullName()
            }

            EFieldType.EMAIL -> {
                uiRegisterState = uiRegisterState.copy(email = value)
                isValidateEmail()
            }

            else -> {
                uiRegisterState = uiRegisterState.copy(password = value)
                isValidatePassword()
            }
        }
    }

    private fun register() {
        Log.i("xxxx++++", "vao day 1112")
        viewModelScope.launch {
            Log.i("xxxx++++", "vao day 111")
            callApisOnThread(
                apis = listOf(
                    authRepository.register(
                        uiRegisterState.run {
                            Person(
                                name = fullName,
                                email = email,
                                password = password
                            )
                        }
                    )
                ),
                onFinish = {},
                onEachSuccess = {
                    uiRegisterState = uiRegisterState.copy(isRegisterSuccess = true)
                }
            )
        }
    }

    private fun isValidateEmail(): Boolean {
        val emailResult = EmailValidation().execute(uiRegisterState.email)
        uiRegisterState = uiRegisterState.copy(emailError = emailResult.errorMessage)
        return emailResult.isSuccess

    }

    private fun isValidatePassword(): Boolean {
        val passwordValid = PasswordValidation().execute(uiRegisterState.password)
        uiRegisterState = uiRegisterState.copy(passwordError = passwordValid.errorMessage)
        return passwordValid.isSuccess
    }

    private fun isValidFullName(): Boolean {
        val fullNameValid = FieldValidation().execute(uiRegisterState.fullName)
        uiRegisterState = uiRegisterState.copy(fullNameError = fullNameValid.errorMessage)
        return fullNameValid.isSuccess
    }
}