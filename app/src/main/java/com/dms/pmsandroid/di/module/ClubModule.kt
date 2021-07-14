package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.Introduce.IntroduceClubApiImpl
import com.dms.pmsandroid.feature.calendar.viewmodel.CalendarViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val clubModule = module{
    single { IntroduceClubApiImpl() }

    viewModel { IntroduceClubViewModel(get(),get()) }

}