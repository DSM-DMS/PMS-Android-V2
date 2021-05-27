package com.dms.pmsandroid.di.login

import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel(get(),get()) }
}