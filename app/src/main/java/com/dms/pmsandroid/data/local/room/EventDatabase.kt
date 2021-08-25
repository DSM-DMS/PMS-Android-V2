package com.dms.pmsandroid.data.local.room

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.dms.pmsandroid.feature.calendar.model.RoomDotTypes
import com.dms.pmsandroid.feature.calendar.model.RoomEvents

@Database(
    entities = [RoomEvents::class, RoomDotTypes::class],
    version = 1,
    exportSchema = false
)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var instance: EventDatabase? = null
        fun getInstance(context: Context): EventDatabase? {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "local_event_database").build()
                Companion.instance = instance
                instance
            }
        }
    }
}