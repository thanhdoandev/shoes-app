package com.example.compose_ui.ui.screens.features.tabs.home.navigations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.navigations.pushToScreen
import com.example.compose_ui.ui.screens.features.tabs.home.Home
import com.example.compose_ui.ui.screens.features.tabs.home.ui.detail.ShoesDetail
import com.example.compose_ui.ui.screens.features.tabs.home.ui.detail.ShoesDetailViewModel
import com.example.compose_ui.ui.screens.features.tabs.home.ui.seach.Search

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    onOpenMenu: () -> Unit
) {
    navigation(
        startDestination = getScreenName(EScreenName.HOME),
        route = getScreenName(EScreenName.HOME_ROUTE)
    ) {
        HOME_SCREENS.forEach { screen ->
            composable(
                route = getScreenName(screen.route),
                arguments = when (screen.route) {
                    EScreenName.SHOES_DETAIL -> {
                        listOf(navArgument("shoesId") { type = NavType.StringType })
                    }

                    else -> listOf()
                }
            ) {
                when (screen.route) {
                    EScreenName.HOME -> {
                        Home(
                            onViewDetail = { shoesId ->
                                navController.navigate("${getScreenName(EScreenName.SHOES)}$shoesId")
                            },
                            onClickSearch = {
                                navController.pushToScreen(EScreenName.SEARCH_SCREEN)
                            },
                            onOpenMenu = onOpenMenu
                        )
                    }

                    EScreenName.SHOES_DETAIL -> {
                        ShoesDetail(
                            viewModel = hiltViewModel<ShoesDetailViewModel>(it, "shoesId")
                        ) {
                            navController.popBackStack()
                        }
                    }

                    EScreenName.SEARCH_SCREEN -> {
                        Search(
                            onClickItem = { id ->
                                navController.navigate("${getScreenName(EScreenName.SHOES)}$id")
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}
