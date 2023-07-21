package com.christianto.natalio.android.lab.navigation

import com.christianto.natalio.android.lab.util.constants.Route.DETAIL_SCREEN_ROUTE
import com.christianto.natalio.android.lab.util.constants.Route.HOME_SCREEN_ROUTE

sealed class MovieScreen (val route: String) {
    object HomeScreen : MovieScreen(HOME_SCREEN_ROUTE)
    object DetailsScreen : MovieScreen(DETAIL_SCREEN_ROUTE)

    fun withArgs(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
    fun fromRoute(route: String?): MovieScreen = when (route?.substringBefore("/")) {
        HomeScreen.route -> HomeScreen
        DetailsScreen.route -> DetailsScreen
        null -> HomeScreen
        else -> throw IllegalArgumentException("Route $route is not recognized")
    }
}