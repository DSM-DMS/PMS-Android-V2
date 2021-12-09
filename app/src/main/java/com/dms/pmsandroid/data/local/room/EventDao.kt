package com.dms.pmsandroid.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: List<RoomEvents>): List<Long>

    @Query("SELECT * FROM events")
    fun getLocalEvent(): Single<List<RoomEvents>>

    @Query("DELETE FROM events")
    fun deleteEvents()
}