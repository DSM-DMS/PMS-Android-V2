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
            Log.d("일정",response.raw().toString())
        },{
            Log.d("일정","예외: $it")
        })
    }

}