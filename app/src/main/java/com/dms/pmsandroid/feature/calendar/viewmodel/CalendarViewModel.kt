package com.dms.pmsandroid.feature.calendar.viewmodel

import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.calendar.CalendarApiImpl

class CalendarViewModel(
    private val calendarApiImpl: CalendarApiImpl,
    sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val accessToken = sharedPreferenceStorage.getInfo("token")

    fun loadSchedules() {
        calendarApiImpl.scheduleApi(accessToken).subscribe({ response ->
            if(response.isSuccessful){

            }
        }, {
        })
    }

}