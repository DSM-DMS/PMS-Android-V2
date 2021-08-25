package com.dms.pmsandroid.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dot_types")
data class RoomDotTypes(val date:String, @ColumnInfo(name="dot_type") val dotType:Int){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}