package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.notify.ProvideNotifyApi
import com.dms.pmsandroid.feature.notify.viewmodel.GalleryDetailViewModel
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel
import com.dms.pmsandroid.feature.notify.viewmodel.NotifyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notifyModule = module {
    single { ProvideNotifyApi() }

    viewModel { GalleryDetailViewModel(get()) }
    viewModel { NotifyViewModel(get(),get()) }
    viewModel { NoticeDetailViewModel(get(),get()) }
}