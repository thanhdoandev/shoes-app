package com.example.compose_ui.ui.navigations

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.screens.auth.navigations.authGraph
import com.example.compose_ui.ui.screens.features.favorites.navigations.favoriteGraph
import com.example.compose_ui.ui.screens.features.home.navigations.homeGraph
import com.example.compose_ui.ui.screens.features.notifications.navigations.notificationGraph
import com.example.compose_ui.ui.screens.features.orders.navigations.orderGraph
import com.example.compose_ui.ui.screens.features.profile.navigations.profileGraph
import com.example.compose_ui.ui.screens.intro.navigations.introGraph
import com.example.compose_ui.ui.theme.primaryColor


@Composable
fun AppNavigation(isSigned: Boolean) {
    val isVisible = rememberSaveable { (mutableStateOf(true)) }

    val navHostController = rememberNavController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    val startRoute = if (isSigned) EScreenName.AUTH_ROUTE else EScreenName.AUTH_ROUTE

    when (navBackStackEntry?.getCurrentRoute()) {
        getScreenName(EScreenName.HOME),
        getScreenName(EScreenName.FAVORITES),
        getScreenName(EScreenName.ORDERS),
        getScreenName(EScreenName.NOTIFICATIONS),
        getScreenName(EScreenName.PROFILE) -> isVisible.value = true

        else -> isVisible.value = false
    }

    val view = LocalView.current
    val color: Color = colorScheme.background
    Scaffold(
        bottomBar = {
            AppBottomTabs(navController = navHostController, isVisible.value)
        }
    ) { padding ->
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor =
                if (navBackStackEntry?.getCurrentRoute() == getScreenName(EScreenName.HOME)) {
                    primaryColor.toArgb()
                } else {
                    color.toArgb()
                }
        }
        NavHost(
            navHostController,
            route = EScreenName.getScreenName(EScreenName.APP_NAV),
            startDestination = EScreenName.getScreenName(startRoute),
            modifier = Modifier.padding(padding),
        ) {
            introGraph(navHostController)
            authGraph(navHostController)
            homeGraph(navHostController)
            favoriteGraph(navHostController)
            orderGraph(navHostController)
            notificationGraph(navHostController)
            profileGraph(navHostController)
        }
    }
}