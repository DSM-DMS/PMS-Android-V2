package com.dms.pmsandroid.feature.calendar.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class RoomEvents(
    val year: Int,
    val month: Int,
    val day: Int,
    val event: String) {
    @PrimaryKey
    var date:String = year.toString() + month.toString() + day.toString()
}