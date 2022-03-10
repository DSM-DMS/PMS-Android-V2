package com.dms.pmsandroid.data.calendar.remote

import com.dms.pmsandroid.data.calendar.dto.CalendarResponse
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.PotatoChipApi
import com.google.gson.JsonObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class CalendarRemoteDatasource(api: PotatoChipApi, private val sharedPreferenceStorage: SharedPreferenceStorage) {

    private val calendarApi = api.retrofit.create(CalendarApi::class.java)

    fun getSchedules(): @NonNull Single<Response<CalendarResponse>> = calendarApi.schedules(sharedPreferenceStorage.getInfo("access_token"))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}