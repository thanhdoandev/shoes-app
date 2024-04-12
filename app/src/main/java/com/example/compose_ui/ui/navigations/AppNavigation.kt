package com.example.compose_ui.ui.navigations

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.screens.auth.navigations.authGraph
import com.example.compose_ui.ui.screens.features.favorites.navigations.favoriteGraph
import com.example.compose_ui.ui.screens.features.home.navigations.homeGraph
import com.example.compose_ui.ui.screens.features.notifications.navigations.notificationGraph
import com.example.compose_ui.ui.screens.features.orders.navigations.orderGraph
import com.example.compose_ui.ui.screens.features.profile.navigations.profileGraph
import com.example.compose_ui.ui.screens.intro.navigations.introGraph


@Composable
fun AppNavigation(isSigned: Boolean) {
    val navHostController = rememberNavController()
    val startRoute = EScreenName.AUTH_ROUTE
    Scaffold() { padding ->
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