@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.compose_ui.ui.navigations

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.compose_ui.R
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName

sealed class BottomTab(
    val route: EScreenName,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    data object Home : BottomTab(EScreenName.HOME_ROUTE, R.string.homeNameTab, Icons.Outlined.Home)
    data object Profile :
        BottomTab(EScreenName.PROFILE_ROUTE, R.string.profileNameTab, Icons.Outlined.Person)

    data object Favorites : BottomTab(
        EScreenName.FAVORITES_ROUTE,
        R.string.favoriteNameTab,
        Icons.Outlined.FavoriteBorder
    )

    data object Orders :
        BottomTab(EScreenName.ORDERS_ROUTE, R.string.orderNameTab, Icons.Outlined.ShoppingCart)

    data object Notifications : BottomTab(
        EScreenName.NOTIFICATIONS_ROUTE,
        R.string.notificationNameTab,
        Icons.Outlined.Notifications
    )
}

private val bottomTabItems = listOf(
    BottomTab.Home,
    BottomTab.Favorites,
    BottomTab.Orders,
    BottomTab.Notifications,
    BottomTab.Profile
)

@Composable
fun AppBottomTabs(navController: NavHostController, isVisible: Boolean) {

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            bottomTabItems.forEach { screen ->
                NavigationBarItem(
                    alwaysShowLabel = true,
                    icon = {
                        Icon(
                            screen.icon,
                            contentDescription = null,
                            modifier = Modifier
                                .height(36.dp)
                                .width(30.dp)
                        )
                    },
                    selected = currentDestination?.hierarchy?.any {
                        it.route == getScreenName(screen.route)
                    } == true,
                    onClick = {
                        val route = when (navBackStackEntry.getCurrentRoute()) {
                            getScreenName(EScreenName.HOME) -> EScreenName.HOME_ROUTE
                            getScreenName(EScreenName.FAVORITES) -> EScreenName.FAVORITES_ROUTE
                            getScreenName(EScreenName.ORDERS) -> EScreenName.ORDERS_ROUTE
                            getScreenName(EScreenName.NOTIFICATIONS) -> EScreenName.NOTIFICATIONS_ROUTE
                            getScreenName(EScreenName.PROFILE) -> EScreenName.PROFILE_ROUTE
                            else -> {}
                        }
                        if (route != screen.route) {
                            navController.navigate(getScreenName(screen.route)) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}
