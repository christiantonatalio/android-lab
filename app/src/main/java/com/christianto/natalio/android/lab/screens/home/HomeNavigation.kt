package com.christianto.natalio.android.lab.screens.home

import androidx.navigation.NavController
import com.christianto.natalio.android.lab.utils.Constants.CITY

fun NavController.backToHomeScreen(city: String) {
    this.apply {
        previousBackStackEntry?.savedStateHandle?.set(CITY, city)
        popBackStack()
    }
}