package com.christianto.natalio.android.lab.di

import com.christianto.natalio.android.lab.network.WeatherApi
import com.christianto.natalio.android.lab.repository.WeatherRepository
import com.christianto.natalio.android.lab.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)


    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherApi
    ) = WeatherRepository(api)
}