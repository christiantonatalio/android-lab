package com.christianto.natalio.android.lab.network

import com.christianto.natalio.android.lab.model.Weather
import com.christianto.natalio.android.lab.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("appid") appid: String = Constants.API_KEY,
        @Query("units") units: String = "metric",
    ): Weather
}