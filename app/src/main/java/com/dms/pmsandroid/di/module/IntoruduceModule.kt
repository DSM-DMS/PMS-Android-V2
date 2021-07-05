package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.Introduce.IntroduceClubApiImpl
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceCompanyViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.MainIntroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val introduceModule = module {
    single { MainIntroViewModel() }

    single { IntroduceClubApiImpl() }

    viewModel { IntroduceClubViewModel(get()) }

    viewModel { IntroduceCompanyViewModel() }

}