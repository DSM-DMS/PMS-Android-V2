package com.dms.pmsandroid.presentation.di.module

import com.dms.pmsandroid.data.mypage.remote.ProvideMyPageApi
import com.dms.pmsandroid.presentation.feature.mypage.viewmodel.ChangePasswordViewModel
import com.dms.pmsandroid.presentation.feature.mypage.viewmodel.MyPageViewModel
import com.dms.pmsandroid.presentation.feature.mypage.viewmodel.OutingContentViewModel
import com.dms.pmsandroid.presentation.feature.mypage.viewmodel.PointContentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myPageModule = module {
    viewModel { MyPageViewModel(get(), get(), get(), get()) }

    single { ProvideMyPageApi(get()) }

    viewModel { OutingContentViewModel(get(), get()) }

    viewModel { ChangePasswordViewModel(get(), get()) }

    viewModel { PointContentViewModel(get(), get()) }

}