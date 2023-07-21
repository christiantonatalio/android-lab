package com.christianto.natalio.android.lab.navigation

sealed class MovieScreen (val route: String) {
    object HomeScreen : MovieScreen("home_screen")
    object DetailsScreen : MovieScreen("detail_screen")

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