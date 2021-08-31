package com.dms.pmsandroid.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface DotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDotType(dotType: List<RoomDotTypes>): List<Long>

    @Query("SELECT dot_type FROM dot_types WHERE date = :date")
    fun getLocalDotTypes(date: String): Single<List<Int>>

    @Query("DELETE FROM dot_types")
    fun deleteTypes()
}