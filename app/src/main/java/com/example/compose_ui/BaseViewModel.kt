package com.example.compose_ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val database: FirebaseFirestore = Firebase.firestore
    private val auth: FirebaseAuth = Firebase.auth

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    protected fun loginToServer(
        email: String,
        password: String,
        onFinish: (isSuccess: Boolean) -> Unit
    ) {
        _isLoading.value = true
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            onFinish(true)
            _isLoading.value = false
        }.addOnFailureListener {
            _isLoading.value = false
        }
    }

    internal fun isUserSigned() = auth.currentUser != null

    @OptIn(DelicateCoroutinesApi::class)
    internal fun userSignOut(onFinish: () -> Unit) {
        _isLoading.value = true
        GlobalScope.launch(Dispatchers.IO) {
            val logout = async { auth.signOut() }
            logout.await()
            _isLoading.value = false
            onFinish()
        }
    }
}