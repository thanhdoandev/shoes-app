package com.example.compose_ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoesApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}