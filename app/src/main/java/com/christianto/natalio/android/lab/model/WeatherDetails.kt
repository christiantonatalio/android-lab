package com.christianto.natalio.android.lab.model

import com.christianto.natalio.android.lab.utils.formatDate
import com.christianto.natalio.android.lab.utils.formatDateTime
import com.christianto.natalio.android.lab.utils.formatDay
import com.christianto.natalio.android.lab.utils.formatDecimals

data class WeatherDetails(
    val clouds: Int? = null,
    val deg: Int? = null,
    val dt: Int? = null,
    val feels_like: FeelsLike? = null,
    val gust: Double? = null,
    val humidity: Int? = null,
    val pop: Double? = null,
    val pressure: Int? = null,
    val speed: Double? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null,
    val temp: Temp? = null,
    val weather: List<WeatherX>? = null
) {
    fun getFormattedSpeed() = formatDecimals(speed ?: 0.0)
    fun getSunriseTime() = formatDateTime(sunrise ?: 0)
    fun getSunsetTime() = formatDateTime(sunset ?: 0)
    fun getFormattedDay() = formatDay(sunset ?: 0)
    fun getFormattedDate() = formatDate(dt ?: 0)
    fun getWeatherIcon() = "https://openweathermap.org/img/wn/${weather?.getOrNull(0)?.icon}.png"
    fun getTemperature() = formatDecimals((temp?.day) ?: 0.0) + "°"
    fun getMaxTemperature() = formatDecimals((temp?.max) ?: 0.0) + "°"
    fun getMinTemperature() = formatDecimals((temp?.min) ?: 0.0) + "°"
    fun getWeatherCondition() = weather?.getOrNull(0)?.main
    fun getWeatherDescription() = weather?.getOrNull(0)?.description
}