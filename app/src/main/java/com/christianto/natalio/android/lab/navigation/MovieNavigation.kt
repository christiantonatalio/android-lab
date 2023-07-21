package com.christianto.natalio.android.lab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.christianto.natalio.android.lab.screens.details.DetailsScreen
import com.christianto.natalio.android.lab.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(MovieScreens.HomeScreen.name) {
            //here we pass where this should lead us to
            HomeScreen(navController)
        }

        composable(MovieScreens.DetailsScreen.name) {
            DetailsScreen(navController)
        }
    }
}