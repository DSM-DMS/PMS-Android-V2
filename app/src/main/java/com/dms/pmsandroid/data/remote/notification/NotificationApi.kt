package com.dms.pmsandroid.data.remote.notification

import com.dms.pmsandroid.presentation.feature.login.model.NotificationRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface NotificationApi {
    @POST("/notification")
    fun subscribeNotification(
        @Header("Authorization") token: String,
        @Body request: NotificationRequest
    ): Single<Response<Unit>>

    @DELETE("/notification")
    fun unSubscribeNotification(@Header("Authorization") token: String): Single<Response<Unit>>

}