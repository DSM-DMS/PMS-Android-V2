package com.dms.pmsandroid.di

import android.app.Application
import com.dms.pmsandroid.di.module.calendarModule
import com.dms.pmsandroid.di.module.loginModule
import com.dms.pmsandroid.di.module.mainModule
import com.dms.pmsandroid.di.module.registerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PmsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PmsApplication)
            modules(
                listOf(
                    mainModule,
                    loginModule,
                    registerModule,
                    calendarModule
                )
            )
        }
    }
}