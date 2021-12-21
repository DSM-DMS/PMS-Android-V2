package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.calendar.remote.ProvideCalendarApi
import com.dms.pmsandroid.data.calendar.repository.CalendarRepository
import com.dms.pmsandroid.data.calendar.repository.CalendarRepositoryImpl
import com.dms.pmsandroid.feature.calendar.viewmodel.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val calendarModule = module {
    single<CalendarRepository> { CalendarRepositoryImpl(get(), get()) }

    single { ProvideCalendarApi(get(), get()) }

    viewModel { CalendarViewModel(get()) }
}