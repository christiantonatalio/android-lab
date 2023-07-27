package com.christianto.natalio.android.lab.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = "note_tbl")
data class Note  constructor(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name = "note_desc")
    val description: String,

    @ColumnInfo(name = "note_date")
    val entryDate: Date = Date.from(Instant.now())
)