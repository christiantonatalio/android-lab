package com.christianto.natalio.android.lab.repository

import com.christianto.natalio.android.lab.data.DataOrException
import com.christianto.natalio.android.lab.model.Weather
import com.christianto.natalio.android.lab.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi
) {
    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}