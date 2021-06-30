package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.feature.introduce.viewmodel.MainIntroViewModel
import org.koin.dsl.module

val introduceModule = module {
    single { MainIntroViewModel() }
}