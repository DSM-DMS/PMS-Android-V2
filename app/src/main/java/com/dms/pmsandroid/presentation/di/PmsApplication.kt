package com.dms.pmsandroid.presentation.di

import android.app.Application
import com.dms.pmsandroid.presentation.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PmsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PmsApplication)
            modules(
                listOf(
                    mainModule,
                    loginModule,
                    registerModule,
                    calendarModule,
                    developerModule,
                    introduceModule,
                    mealModule,
                    myPageModule,
                    notifyModule,
                    notificationModule,
                    networkModule
                )
            )
        }
    }
}