package com.example.compose_ui.ui.cores.data.remotes

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class FirebaseServices {
    private val firebase = Firebase.auth

    val auth: FirebaseAuth
        get() = firebase
}