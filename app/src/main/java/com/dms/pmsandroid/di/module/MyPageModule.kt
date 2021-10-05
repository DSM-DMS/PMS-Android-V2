package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.mypage.ProvideMyPageApi
import com.dms.pmsandroid.feature.mypage.viewmodel.ChangePasswordViewModel
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import com.dms.pmsandroid.feature.mypage.viewmodel.OutingContentViewModel
import com.dms.pmsandroid.feature.mypage.viewmodel.PointContentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myPageModule = module {
    viewModel { MyPageViewModel(get(), get(), get()) }

    single { ProvideMyPageApi() }

    viewModel { OutingContentViewModel(get(), get()) }

    viewModel { ChangePasswordViewModel(get(), get()) }

    viewModel { PointContentViewModel(get(), get()) }

}