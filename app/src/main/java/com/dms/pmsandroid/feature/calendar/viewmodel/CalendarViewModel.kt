package com.dms.pmsandroid.feature.calendar.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.calendar.CalendarApiProvider

class CalendarViewModel(
    private val calendarApiProvider: CalendarApiProvider,
    sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {
    val accessToken = sharedPreferenceStorage.getInfo("token")

    fun loadSchedules() {
        calendarApiProvider.scheduleApi(accessToken).subscribe({response->
            Log.d("스케줄",response.raw().toString()+"토큰:$accessToken")
        },{
            Log.d("스케줄",it.toString())
        })
    }

}