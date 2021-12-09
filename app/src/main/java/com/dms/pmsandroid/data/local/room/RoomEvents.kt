package com.dms.pmsandroid.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class RoomEvents(
    @PrimaryKey val date: String,
    val event: String,
    val dot:String
)