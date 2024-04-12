package com.example.compose_ui.ui.screens.auth.register

import androidx.lifecycle.ViewModel
import com.example.compose_ui.ui.data.enums.EFieldType
import com.example.compose_ui.ui.data.vo.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {
    private val _user = MutableStateFlow(Person(name = "", email = "", password = ""))
    val user: StateFlow<Person> = _user.asStateFlow()

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
}