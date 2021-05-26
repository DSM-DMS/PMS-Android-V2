package com.dms.pmsandroid.di.main

import com.dms.pmsandroid.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module{
    viewModel { MainViewModel(get()) }
}