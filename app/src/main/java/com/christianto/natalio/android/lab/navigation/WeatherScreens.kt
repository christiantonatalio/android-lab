package com.christianto.natalio.android.lab.navigation

enum class WeatherScreens {
    HomeScreen,
    AboutScreen,
    FavoriteScreen,
    SearchScreen,
    SettingScreen
}

fun WeatherScreens.withArgs(vararg args: String) : String {
    return buildString {
        append(this@withArgs.name)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}