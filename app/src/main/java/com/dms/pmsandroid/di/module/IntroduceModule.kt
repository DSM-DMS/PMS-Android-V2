package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.Introduce.ProvideIntroduceClubApi
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubDetailViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceCompanyViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.MainIntroduceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val introduceModule = module {
    viewModel{ MainIntroduceViewModel() }

    single { ProvideIntroduceClubApi() }

    viewModel { IntroduceClubViewModel(get(),get()) }

    viewModel { IntroduceCompanyViewModel(get()) }

    viewModel {IntroduceClubDetailViewModel(get(),get())}

}