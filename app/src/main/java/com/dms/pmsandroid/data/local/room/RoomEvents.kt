package com.dms.pmsandroid.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
class RoomEvents(
    @PrimaryKey val date: String,
    val events: List<String>
)