package com.example.compose_ui.ui.screens.auth.navigations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.navigations.pushToScreen
import com.example.compose_ui.ui.navigations.startNewDestination
import com.example.compose_ui.ui.screens.auth.login.Login
import com.example.compose_ui.ui.screens.auth.register.Register
import com.example.compose_ui.ui.screens.features.home.Home

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(
        startDestination = EScreenName.getScreenName(EScreenName.LOGIN),
        route = EScreenName.getScreenName(EScreenName.AUTH_ROUTE)
    ) {
        AUTH_SCREENS.forEach { screen ->
            composable(EScreenName.getScreenName(screen.route)) {
                when (screen.route) {
                    EScreenName.LOGIN -> {
                        Login(
                            onRegister = { navController.pushToScreen(EScreenName.REGISTER) }
                        ) {
                            navController.startNewDestination(EScreenName.HOME_ROUTE)
                        }
                    }

                    EScreenName.REGISTER -> {
                        Register(onBack = { navController.popBackStack() })
                    }

                    EScreenName.HOME -> {
                        Home()
                    }

                    else -> {
                    }
                }
            }
        }
    }
}