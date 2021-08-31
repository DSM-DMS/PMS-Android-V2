package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.local.room.DotDao
import com.dms.pmsandroid.data.local.room.EventDao
import com.dms.pmsandroid.data.local.room.EventDatabase
import com.dms.pmsandroid.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainModule = module {
    single { SharedPreferenceStorage(androidApplication()) }

    fun provideDao(db: EventDatabase): EventDao = db.eventDao()
    fun provideDotDao(db:EventDatabase):DotDao = db.dotDao()
    single { EventDatabase.getInstance(androidApplication()) }
    single { provideDao(get()) }
    single { provideDotDao(get()) }

    single { MainViewModel(get(), get()) }
}