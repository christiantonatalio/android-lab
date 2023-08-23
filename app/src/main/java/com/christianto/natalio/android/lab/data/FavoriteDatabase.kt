package com.christianto.natalio.android.lab.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.christianto.natalio.android.lab.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase(){
    abstract fun favDao() : FavoriteDatabaseDao
}