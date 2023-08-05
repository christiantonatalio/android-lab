package com.christianto.natalio.android.lab.model

data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val weatherDetails: List<WeatherDetails>,
    val message: Double
)