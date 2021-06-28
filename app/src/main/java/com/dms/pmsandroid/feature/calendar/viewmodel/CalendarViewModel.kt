package com.dms.pmsandroid.feature.calendar.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.calendar.CalendarApiImpl

class CalendarViewModel(
    private val calendarApiImpl: CalendarApiImpl,
    sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {
    val accessToken = sharedPreferenceStorage.getInfo("token")

    fun loadSchedules() {
        calendarApiProvider.scheduleApi(accessToken).subscribe({ response ->
        }, {
        })
    }

}