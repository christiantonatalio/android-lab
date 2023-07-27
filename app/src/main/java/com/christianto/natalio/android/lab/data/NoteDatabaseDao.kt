package com.christianto.natalio.android.lab.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.christianto.natalio.android.lab.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * from note_tbl")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from note_tbl where id =:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from note_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}