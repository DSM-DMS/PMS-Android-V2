package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.ui.MainActivity
import com.dms.pmsandroid.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainModule = module{
    single { SharedPreferenceStorage(androidApplication()) }

    single { MainViewModel(get(),get()) }
}