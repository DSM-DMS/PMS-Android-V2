package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.MainIntroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val developerModule = module {

    viewModel { IntroduceDeveloperViewModel() }

}