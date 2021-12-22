package com.dms.pmsandroid.presentation.di.module

import com.dms.pmsandroid.data.remote.introduce.ProvideIntroduceClubApi
import com.dms.pmsandroid.presentation.feature.introduce.viewmodel.IntroduceClubDetailViewModel
import com.dms.pmsandroid.presentation.feature.introduce.viewmodel.IntroduceClubViewModel
import com.dms.pmsandroid.presentation.feature.introduce.viewmodel.IntroduceCompanyViewModel
import com.dms.pmsandroid.presentation.feature.introduce.viewmodel.MainIntroduceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val introduceModule = module {
    viewModel{ MainIntroduceViewModel() }

    single { ProvideIntroduceClubApi(get()) }

    viewModel { IntroduceClubViewModel(get(),get()) }

    viewModel { IntroduceCompanyViewModel(get()) }

    viewModel {IntroduceClubDetailViewModel(get(),get())}

}