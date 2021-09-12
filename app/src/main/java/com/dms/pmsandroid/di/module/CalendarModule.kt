package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.calendar.CalendarApiImpl
import com.dms.pmsandroid.feature.calendar.viewmodel.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val calendarModule = module {
    single { CalendarApiImpl() }

    viewModel { CalendarViewModel(get(),get(),get()) }
}