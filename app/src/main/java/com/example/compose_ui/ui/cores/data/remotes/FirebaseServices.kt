package com.example.compose_ui.ui.cores.data.remotes

import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.response.ApiResponse
import com.example.compose_ui.ui.extensions.getStringValue
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await


class FirebaseServices {
    private val auth: FirebaseAuth
        get() = Firebase.auth
    private val dataBase: FirebaseFirestore
        get() = Firebase.firestore

    var isLoggedIn: ApiResponse<Boolean> =
        if (auth.currentUser == null) ApiResponse.Success(true) else ApiResponse.Success(false)

    suspend fun login(email: String, password: String): ApiResponse<Person> {
        return try {
            val resultLogin = auth.signInWithEmailAndPassword(email, password).await()
            if (resultLogin.user != null) {
                ApiResponse.Success(Person(email, password))
            } else {
                ApiResponse.Error("Incorrect Email or Password. Please help me check again!")
            }
        } catch (e: Exception) {
            ApiResponse.Error(e.message.getStringValue())
        }
    }
}