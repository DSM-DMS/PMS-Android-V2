package com.dms.pmsandroid.presentation.di.module

import com.dms.pmsandroid.presentation.feature.login.viewmodel.FindPassWordViewModel
import com.dms.pmsandroid.presentation.feature.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }

    viewModel { FindPassWordViewModel(get(), get()) }
}