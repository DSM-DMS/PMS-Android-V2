package com.dms.pmsandroid.di.module

import android.app.Dialog
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.feature.mypage.ui.ChangeNameDialog
import com.dms.pmsandroid.feature.mypage.viewmodel.ChangePasswordViewModel
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import com.dms.pmsandroid.feature.mypage.viewmodel.OutingContentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myPageModule = module{
    viewModel { MyPageViewModel(get(),get())}

    viewModel {OutingContentViewModel(get(),get()) }

    viewModel { ChangePasswordViewModel(get(),get()) }

}