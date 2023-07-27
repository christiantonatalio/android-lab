package com.christianto.natalio.android.lab.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.christianto.natalio.android.lab.model.Note
import com.christianto.natalio.android.lab.util.DateConverter
import com.christianto.natalio.android.lab.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDatabaseDao
}