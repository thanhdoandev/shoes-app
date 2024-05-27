package com.example.compose_ui.ui.navigations

import androidx.lifecycle.SavedStateHandle
import com.example.compose_ui.ui.components.bases.BaseViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppNavigationViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {
    val auth: FirebaseAuth = Firebase.auth

    private var _isSigned: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSigned = _isSigned.asStateFlow()

    init {
        _isSigned.value = auth.currentUser != null
    }

    fun userLogout() {
        userSignOut {
            _isSigned.value = false
        }
    }

    fun userLoginSuccess() {
        _isSigned.value = auth.currentUser != null
    }
}