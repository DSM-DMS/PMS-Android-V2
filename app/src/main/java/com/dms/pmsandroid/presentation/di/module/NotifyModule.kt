package com.dms.pmsandroid.presentation.di.module

import com.dms.pmsandroid.data.remote.notify.ProvideNotifyApi
import com.dms.pmsandroid.presentation.feature.notify.viewmodel.GalleryDetailViewModel
import com.dms.pmsandroid.presentation.feature.notify.viewmodel.NoticeDetailViewModel
import com.dms.pmsandroid.presentation.feature.notify.viewmodel.NotifyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notifyModule = module {
    single { ProvideNotifyApi(get()) }

    viewModel { GalleryDetailViewModel(get()) }
    viewModel { NotifyViewModel(get(),get()) }
    viewModel { NoticeDetailViewModel(get(),get()) }
}