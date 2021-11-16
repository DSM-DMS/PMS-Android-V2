package com.dms.pmsandroid.data.remote.calendar

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CalendarApi {
    @GET("potatochips.live/api/calendar")
    fun schedules(@Header("Authorization") accessToken: String): Single<Response<JsonObject>>

}