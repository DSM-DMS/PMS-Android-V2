package com.dms.pmsandroid.feature.notify.viewmodel

import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.notify.NotifyApiImpl

class NoticeDetailViewModel(
    private val notifyApiImpl: NotifyApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {
    fun getNoticeDetail(id: Int) {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        notifyApiImpl.getNoticeDetail(accessToken,id).subscribe { response->

        }
    }
}