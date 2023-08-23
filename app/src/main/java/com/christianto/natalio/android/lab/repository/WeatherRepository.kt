package com.christianto.natalio.android.lab.repository

import com.christianto.natalio.android.lab.data.DataOrException
import com.christianto.natalio.android.lab.data.FavoriteDatabaseDao
import com.christianto.natalio.android.lab.model.Favorite
import com.christianto.natalio.android.lab.model.Weather
import com.christianto.natalio.android.lab.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val dao: FavoriteDatabaseDao
) {
    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

    suspend fun addFav(favCity: Favorite) = dao.insert(favCity)

    suspend fun deleteFav(favCity: Favorite) = dao.deleteFav(favCity)

    fun getAllFavorites(): Flow<List<Favorite>> =
        dao.getFavorites().flowOn(Dispatchers.IO).conflate()

}