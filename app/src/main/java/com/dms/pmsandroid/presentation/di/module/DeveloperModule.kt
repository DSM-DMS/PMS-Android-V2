package com.dms.pmsandroid.presentation.di.module

import com.dms.pmsandroid.presentation.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val developerModule = module {

    viewModel { IntroduceDeveloperViewModel() }

}