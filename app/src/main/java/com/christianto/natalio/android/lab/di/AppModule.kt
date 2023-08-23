package com.christianto.natalio.android.lab.di

import android.content.Context
import androidx.room.Room
import com.christianto.natalio.android.lab.data.FavoriteDatabase
import com.christianto.natalio.android.lab.data.FavoriteDatabaseDao
import com.christianto.natalio.android.lab.network.WeatherApi
import com.christianto.natalio.android.lab.repository.WeatherRepository
import com.christianto.natalio.android.lab.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        api: WeatherApi,
        dao: FavoriteDatabaseDao
    ) = WeatherRepository(api, dao)

    @Provides
    @Singleton
    fun provideFavDao(favoriteDatabase: FavoriteDatabase): FavoriteDatabaseDao =
        favoriteDatabase.favDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): FavoriteDatabase =
        Room.databaseBuilder(context, FavoriteDatabase::class.java, "fav_tbl")
            .fallbackToDestructiveMigration().build()
}