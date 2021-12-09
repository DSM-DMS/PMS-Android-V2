package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.notification.ProvideNotificationApi
import org.koin.dsl.module

val notificationModule = module {
    single { ProvideNotificationApi(get(),get()) }
}