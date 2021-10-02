package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.feature.login.viewmodel.FindPassWordViewModel
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel(get(),get()) }

    viewModel { FindPassWordViewModel() }
}