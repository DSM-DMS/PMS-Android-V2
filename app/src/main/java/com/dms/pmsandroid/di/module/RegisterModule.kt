package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.login.LoginApiImpl
import com.dms.pmsandroid.feature.login.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    single { LoginApiImpl() }

    viewModel { RegisterViewModel(get()) }
}