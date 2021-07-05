package com.dms.pmsandroid.data.remote.calendar

import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.feature.calendar.model.SchedulesResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class CalendarApiImpl {
    private fun provideCalendarApi(): CalendarApi = ApiProvider.jiWooRetrofitBuilder.create(CalendarApi::class.java)

    fun scheduleApi(request: String): @NonNull Single<Response<SchedulesResponse>> = provideCalendarApi().schedules(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}