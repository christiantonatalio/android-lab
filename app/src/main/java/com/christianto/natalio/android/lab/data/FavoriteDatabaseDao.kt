package com.christianto.natalio.android.lab.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.christianto.natalio.android.lab.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDatabaseDao {
    @Query("SELECT * from fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT COUNT() FROM fav_tbl WHERE city = :city")
    fun getCount(city: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favCity: Favorite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteFav(favCity: Favorite)
}