package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.Introduce.IntroduceClubApiImpl
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubViewModel
import com.dms.pmsandroid.feature.mypage.viewmodel.PointContentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pointModule = module{
    single { MyPageApiImpl() }

    viewModel {PointContentViewModel(get())}

}