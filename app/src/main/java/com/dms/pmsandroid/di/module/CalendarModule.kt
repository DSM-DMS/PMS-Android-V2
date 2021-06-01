package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.feature.calendar.viewmodel.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val calendarModule = module {
    viewModel { CalendarViewModel(get(),get()) }
}