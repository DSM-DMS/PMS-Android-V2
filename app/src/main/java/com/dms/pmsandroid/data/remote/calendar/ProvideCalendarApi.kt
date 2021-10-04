package com.dms.pmsandroid.data.remote.calendar

import com.dms.pmsandroid.data.remote.ApiProvider
import com.google.gson.JsonObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class ProvideCalendarApi {
    private fun provideCalendarApi(): CalendarApi = ApiProvider.jiWooRetrofitBuilder.create(CalendarApi::class.java)

    fun scheduleApi(request: String): @NonNull Single<Response<JsonObject>> = provideCalendarApi().schedules(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}