package com.dms.pmsandroid.data.remote.calendar

import com.google.gson.JsonObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import retrofit2.Retrofit

class ProvideCalendarApi(retrofit: Retrofit) {

    private val calendarApi = retrofit.create(CalendarApi::class.java)

    fun scheduleApi(request: String): @NonNull Single<Response<JsonObject>> = calendarApi.schedules(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}