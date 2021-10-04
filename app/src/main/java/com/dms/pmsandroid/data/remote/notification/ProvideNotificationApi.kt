package com.dms.pmsandroid.data.remote.notification

import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.feature.login.model.NotificationRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class ProvideNotificationApi(private val localStorage: SharedPreferenceStorage) {
    private fun provideApi(): NotificationApi =
        ApiProvider.jungBinRetroFitBuilder.create(NotificationApi::class.java)

    fun subscribeNotification(): @NonNull Single<Response<Unit>> {
        val accessToken = localStorage.getInfo("access_token")
        val deviceToken = localStorage.getInfo("device_token")
        val request = NotificationRequest(deviceToken)
        return provideApi().subscribeNotification(accessToken, request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun unSubscribeNotification(): @NonNull Single<Response<Unit>> {
        val accessToken = localStorage.getInfo("access_token")
        return provideApi().unSubscribeNotification(accessToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}