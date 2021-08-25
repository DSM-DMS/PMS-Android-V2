package com.dms.pmsandroid.feature.calendar.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dot_types")
data class RoomDotTypes(@PrimaryKey val date:String, @ColumnInfo(name="dot_type") val dotType:Int)