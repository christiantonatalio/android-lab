package com.christianto.natalio.android.lab.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date: Date) : Long = date.time

    @TypeConverter
    fun dateFromTimestamp(timeStamp: Long): Date = Date(timeStamp)
}