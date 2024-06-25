package com.example.compose_ui.ui.navigations

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_ui.ui.cores.data.enums.EScreenName
import com.example.compose_ui.ui.cores.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.navigations.components.AppBottomTabs
import com.example.compose_ui.ui.navigations.components.MenuContent
import com.example.compose_ui.ui.screens.auth.navigations.authGraph
import com.example.compose_ui.ui.screens.features.menus.deliveries.navigations.deliveriesGraph
import com.example.compose_ui.ui.screens.features.menus.histories.navigations.historiesGraph
import com.example.compose_ui.ui.screens.features.menus.settings.navigations.settingGraph
import com.example.compose_ui.ui.screens.features.tabs.favorites.navigations.favoriteGraph
import com.example.compose_ui.ui.screens.features.tabs.home.navigations.homeGraph
import com.example.compose_ui.ui.screens.features.tabs.home.notifications.navigations.notificationGraph
import com.example.compose_ui.ui.screens.features.tabs.orders.navigations.orderGraph
import com.example.compose_ui.ui.screens.features.tabs.profile.navigations.profileGraph
import com.example.compose_ui.ui.screens.intro.navigations.introGraph
import com.example.compose_ui.ui.theme.CustomComposeTheme
import com.example.compose_ui.ui.theme.bgPage
import com.example.compose_ui.ui.theme.primaryColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Stable
class AppLogicState(private val navBack: NavBackStackEntry?) {
    val isBottomTabVisible: Boolean
        get() = isAppState(true)

    val isMenuBarVisible: Boolean
        get() = isAppState(false)

    private fun isAppState(isBottom: Boolean = false): Boolean {
        val isBottomTab: Boolean
        val isMenuBar: Boolean
        when (navBack?.getCurrentRoute()) {
            getScreenName(EScreenName.HOME) -> {
                isBottomTab = true
                isMenuBar = true
            }

            getScreenName(EScreenName.FAVORITES),
            getScreenName(EScreenName.ORDERS),
            getScreenName(EScreenName.NOTIFICATIONS),
            getScreenName(EScreenName.PROFILE) -> {
                isBottomTab = true
                isMenuBar = false
            }

            getScreenName(EScreenName.HISTORY),
            getScreenName(EScreenName.DELIVERY),
            getScreenName(EScreenName.SETTINGS) -> {
                isBottomTab = true
                isMenuBar = false
            }

            else -> {
                isBottomTab = false
                isMenuBar = false
            }
        }

        return if (isBottom) isBottomTab else isMenuBar
    }
}

@Composable
fun AppNavigation(
    enableDarkMode: MutableState<Boolean>,
    viewModel: AppNavigationViewModel = hiltViewModel(),
) {
    val isSigned = viewModel.isSigned
    val isBottomTabVisible = rememberSaveable { (mutableStateOf(false)) }
    val isMenuVisible = rememberSaveable { mutableStateOf(false) }

    val navHostController = rememberNavController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val startRoute = if (isSigned) EScreenName.HOME_ROUTE else EScreenName.AUTH_ROUTE

    LaunchedEffect(navBackStackEntry) {
        AppLogicState(navBackStackEntry).apply {
            isBottomTabVisible.value = this.isBottomTabVisible
            isMenuVisible.value = this.isMenuBarVisible
        }
    }

    fun openMenu() {
        coroutineScope.launch {
            drawerState.open()
        }
    }

    fun closeMenu() {
        coroutineScope.launch {
            drawerState.close()
        }
    }

    val view = LocalView.current
    val color: Color =
        (if (enableDarkMode.value && isSigned || drawerState.isOpen) primaryColor else if (enableDarkMode.value && !isSigned) CustomComposeTheme.appCustomColors.bgColor else bgPage)

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.fillMaxSize(),
        gesturesEnabled = isMenuVisible.value,
        drawerContent = {
            MenuContent(
                isDarkMode = enableDarkMode.value,
                navBackStackEntry = navBackStackEntry,
                onClickItem = {
                    closeMenu()
                    if (navBackStackEntry?.getCurrentRoute() != getScreenName(it)) {
                        navHostController.startNewDestination(it, isSaveSate = false)
                    }
                },
                onLogout = {
                    closeMenu()
                    coroutineScope.launch(Dispatchers.IO) {
                        viewModel.userLogout()
                    }
                },
                onSwitch = {
                    enableDarkMode.value = !enableDarkMode.value
                })
        }, content = {
            Scaffold(
                bottomBar = {
                    AppBottomTabs(navController = navHostController, isBottomTabVisible.value)
                }
            ) { padding ->

                fun setSystemBarColor(color: Color) {
                    val window = (view.context as Activity).window
                    window.statusBarColor = color.toArgb()
                }

                SideEffect {
                    setSystemBarColor(
                        if (navBackStackEntry?.getCurrentRoute() == getScreenName(EScreenName.HOME)) {
                            primaryColor
                        } else color
                    )
                }

                fun openMenuFromSideBar() {
                    setSystemBarColor(primaryColor)
                    openMenu()
                }

                NavHost(
                    navHostController,
                    route = EScreenName.getScreenName(EScreenName.APP_NAV),
                    startDestination = EScreenName.getScreenName(startRoute),
                    modifier = Modifier.padding(padding),
                ) {
                    introGraph(navHostController)
                    authGraph(navHostController) {
                        coroutineScope.launch(Dispatchers.IO) {
                            viewModel.userLoginSuccess()
                        }
                    }

                    homeGraph(navHostController) {
                        openMenu()
                    }
                    favoriteGraph(navHostController)
                    orderGraph(navHostController)
                    notificationGraph(navHostController)
                    profileGraph(navHostController)

                    historiesGraph(navHostController) {
                        openMenuFromSideBar()
                    }
                    deliveriesGraph(navHostController) {
                        openMenuFromSideBar()
                    }
                    settingGraph(navHostController) {
                        openMenuFromSideBar()
                    }
                }
            }
        }
    )
}

