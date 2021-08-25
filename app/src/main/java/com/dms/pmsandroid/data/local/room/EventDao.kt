package com.dms.pmsandroid.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dms.pmsandroid.feature.calendar.model.RoomDotTypes
import com.dms.pmsandroid.feature.calendar.model.RoomEvents

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(vararg event:RoomEvents)

    @Insert
    fun insertDotType(vararg dotType:RoomDotTypes)

    @Query("Select event from events WHERE date = :date")
    fun getLocalEvent(date:String)
}