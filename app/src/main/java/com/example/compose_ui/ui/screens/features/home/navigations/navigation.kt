package com.example.compose_ui.ui.screens.features.home.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.enums.EScreenName.Companion.getScreenName
import com.example.compose_ui.ui.navigations.pushToScreen
import com.example.compose_ui.ui.screens.features.home.Home
import com.example.compose_ui.ui.screens.features.home.ui.detail.ShoesDetail
import com.example.compose_ui.ui.screens.features.home.ui.seach.SearchScreen

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
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
                            onViewDetail = {
                                navController.navigate("${getScreenName(EScreenName.SHOES)}$it")
                            },
                            onClickSearch = {
                                navController.pushToScreen(EScreenName.SEARCH_SCREEN)
                            }
                        )
                    }

                    EScreenName.SHOES_DETAIL -> {
                        ShoesDetail(it.arguments?.getString("shoesId").toString()) {
                            navController.popBackStack()
                        }
                    }

                    EScreenName.SEARCH_SCREEN -> {
                        SearchScreen()
                    }

                    else -> {}
                }
            }
        }
    }
}
