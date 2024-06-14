package com.example.compose_ui.ui.screens.features.tabs.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.bases.ContainerPage
import com.example.compose_ui.ui.components.cores.JPButton

@Composable
fun Profile(
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onLogout: () -> Unit = {}
) {
    val isLoading by viewModel.uiState.collectAsState()
    val isLogoutSuccess by viewModel.isLogoutSuccess.collectAsState()

    LaunchedEffect(isLogoutSuccess) {
        if (isLogoutSuccess) onLogout()
    }

    ContainerPage(isBack = false, title = "Profile") {
        JPButton(label = "Logout") {
            viewModel.onLogout()
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ProfilePreview() {
    Profile()
}