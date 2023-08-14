package com.christianto.natalio.android.lab.screens.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.christianto.natalio.android.lab.navigation.WeatherScreens

fun NavController.navigateToSearchScreen() {
    this.navigate(WeatherScreens.SearchScreen.name)
}
fun NavGraphBuilder.searchScreen(onBackClicked: (String) -> Unit) {
    composable(WeatherScreens.SearchScreen.name) {
        SearchScreen(onBackClicked)
    }
}