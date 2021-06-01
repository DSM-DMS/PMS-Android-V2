package com.dms.pmsandroid.feature.calendar.viewmodel

import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.calendar.CalendarApiProvider

class CalendarViewModel(
    private val calendarApiProvider: CalendarApiProvider,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {
    val accessToken = sharedPreferenceStorage.getInfo("access-token")
    
    fun loadSchedules() {
        calendarApiProvider.scheduleApi(accessToken).subscribe({response->

        },{

        })
    }

}