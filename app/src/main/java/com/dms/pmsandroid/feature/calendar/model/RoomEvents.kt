package com.dms.pmsandroid.feature.calendar.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class RoomEvents(@ColumnInfo val year:Int, @ColumnInfo val month:Int, @ColumnInfo val day:Int, @ColumnInfo val event:String) {
    @PrimaryKey var date = year.toString()+month.toString()+day.toString()
}