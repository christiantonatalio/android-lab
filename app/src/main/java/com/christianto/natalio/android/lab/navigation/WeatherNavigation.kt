package com.christianto.natalio.android.lab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.christianto.natalio.android.lab.screens.home.HomeScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.HomeScreen.name
    ){
        composable(WeatherScreens.HomeScreen.name) {
            HomeScreen()
        }
    }
}