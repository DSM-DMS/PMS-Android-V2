package com.dms.pmsandroid.data.remote.notification

import retrofit2.http.POST

interface NotificationApi {
    @POST("/notification")
    fun subscribeNotification()
}