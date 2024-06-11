package com.example.compose_ui.ui.screens.auth.navigations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.cores.data.enums.EScreenName
import com.example.compose_ui.ui.navigations.pushToScreen
import com.example.compose_ui.ui.screens.auth.login.Login
import com.example.compose_ui.ui.screens.auth.login.LoginViewModel
import com.example.compose_ui.ui.screens.auth.register.Register
import com.example.compose_ui.ui.screens.auth.register.RegisterViewModels

fun NavGraphBuilder.authGraph(navController: NavController, onGoHome: () -> Unit) {
    navigation(
        startDestination = EScreenName.getScreenName(EScreenName.LOGIN),
        route = EScreenName.getScreenName(EScreenName.AUTH_ROUTE)
    ) {
        AUTH_SCREENS.forEach { screen ->
            composable(EScreenName.getScreenName(screen.route)) {
                when (screen.route) {
                    EScreenName.LOGIN -> {
                        Login(
                            viewModel = hiltViewModel<LoginViewModel>(),
                            onRegister = { navController.pushToScreen(EScreenName.REGISTER) },
                            onOpenHome = onGoHome
                        )
                    }

                    EScreenName.REGISTER -> {
                        Register(
                            viewModel = hiltViewModel<RegisterViewModels>(),
                            onBack = { navController.popBackStack() },
                            onOpenHome = onGoHome
                        )
                    }

                    else -> {
                    }
                }
            }
        }
    }
}