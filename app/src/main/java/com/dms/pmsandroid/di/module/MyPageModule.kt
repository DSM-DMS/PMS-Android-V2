package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myPageModule = module{
    viewModel { MyPageViewModel(get(),get()) }


}