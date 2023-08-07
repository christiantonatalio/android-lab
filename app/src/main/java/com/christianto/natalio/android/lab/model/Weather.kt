package com.christianto.natalio.android.lab.model

data class Weather(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    val message: Double? = null,
    val list: List<WeatherDetails>? = null
)