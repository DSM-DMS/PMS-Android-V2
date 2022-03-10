package com.dms.pmsandroid.data.local.room

import android.content.Context
import androidx.room.*

@Database(
    entities = [RoomEvents::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var instance: EventDatabase? = null
        fun getInstance(context: Context): EventDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "local_event_database"
                ).build()
                Companion.instance = instance
                instance
            }
        }
    }
}