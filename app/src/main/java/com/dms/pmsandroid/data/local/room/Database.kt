package com.dms.pmsandroid.data.local.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dms.pmsandroid.feature.calendar.model.RoomDotTypes
import com.dms.pmsandroid.feature.calendar.model.RoomEvents

@androidx.room.Database(
    entities = [RoomEvents::class, RoomDotTypes::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var instance: Database? = null
        fun getInstance(context: Context): Database? {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "logged_in_user_db"
                ).build()
                Companion.instance = instance
                instance
            }
        }
    }

}