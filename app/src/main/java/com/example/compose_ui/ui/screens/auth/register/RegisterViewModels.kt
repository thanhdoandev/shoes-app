package com.example.compose_ui.ui.screens.auth.register

import androidx.lifecycle.SavedStateHandle
import com.example.compose_ui.ui.components.bases.BaseViewModel
import com.example.compose_ui.ui.cores.data.enums.EFieldType
import com.example.compose_ui.ui.cores.data.vo.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class Login(val int: Int)

@HiltViewModel
class RegisterViewModels @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    private val _user = MutableStateFlow(Person(name = "", email = "", password = ""))
    val user: StateFlow<Person> = _user.asStateFlow()

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess = _isSuccess.asStateFlow()

    internal fun userDataChange(type: EFieldType, value: String) {
        _user.update { person ->
            person.copy().apply {
                when (type) {
                    EFieldType.YOUR_NAME -> name = value
                    EFieldType.EMAIL -> email = value
                    else -> password = value
                }
            }
        }
    }

    internal fun registerAccount() {
        registerAccountToFirebaseServer(_user.value) {
            if (it) _isSuccess.value = true
        }
    }
}