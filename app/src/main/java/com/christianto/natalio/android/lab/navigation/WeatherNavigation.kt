package com.christianto.natalio.android.lab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.christianto.natalio.android.lab.screens.home.HomeScreen
import com.christianto.natalio.android.lab.screens.home.backToHomeScreen
import com.christianto.natalio.android.lab.screens.search.navigateToSearchScreen
import com.christianto.natalio.android.lab.screens.search.searchScreen
import com.christianto.natalio.android.lab.utils.Constants.CITY

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.HomeScreen.name
    ) {
        composable(WeatherScreens.HomeScreen.name) { entry ->
            HomeScreen(
                onOptionsClicked = {},
                onSearchClicked = { navController.navigateToSearchScreen() },
                city = entry.savedStateHandle.get(CITY) as? String
            )
        }
        searchScreen(onBackClicked = navController::backToHomeScreen)
    }
}