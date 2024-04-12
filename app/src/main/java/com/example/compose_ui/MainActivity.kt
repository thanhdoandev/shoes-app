package com.example.compose_ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.compose_ui.ui.navigations.AppNavigation
import com.example.compose_ui.ui.theme.Compose_uiTheme

private val viewModel = BaseViewModel()

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLoading by viewModel.isLoading.collectAsState()
            Compose_uiTheme(isLoading = isLoading) {
                AppNavigation(isSigned = viewModel.isUserSigned())
            }
        }
    }
}