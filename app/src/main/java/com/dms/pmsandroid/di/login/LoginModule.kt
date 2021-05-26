package com.dms.pmsandroid.di.login

import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {

    single { SharedPreferenceStorage(get()) }

    viewModel { LoginViewModel(get(),get()) }
}