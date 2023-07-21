package com.christianto.natalio.android.lab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.christianto.natalio.android.lab.screens.details.DetailsScreen
import com.christianto.natalio.android.lab.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreen.HomeScreen.route
    ) {
        composable(MovieScreen.HomeScreen.route) {
            //here we pass where this should lead us to
            HomeScreen(navController)
        }

        composable(
            route = MovieScreen.DetailsScreen.route + "/{movie_title}",
            arguments = listOf(
                navArgument(name = "movie_title") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            DetailsScreen(navController, entry.arguments?.getString("movie_title"))
        }
    }
}