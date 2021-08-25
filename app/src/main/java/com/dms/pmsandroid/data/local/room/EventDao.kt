package com.dms.pmsandroid.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dms.pmsandroid.feature.calendar.model.RoomDotTypes
import com.dms.pmsandroid.feature.calendar.model.RoomEvents
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: RoomEvents): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDotType(dotType: RoomDotTypes): Completable

    @Query("Select event from events WHERE date = :key")
    fun getLocalEvent(key: String): Single<List<RoomEvents>>

    @Query("Select dot_type from dot_types WHERE date = :key")
    fun getDotType(key: String): Single<List<RoomDotTypes>>
}