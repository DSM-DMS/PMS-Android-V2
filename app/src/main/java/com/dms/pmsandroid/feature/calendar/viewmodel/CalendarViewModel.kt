package com.dms.pmsandroid.feature.calendar.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.calendar.CalendarApiImpl
import com.google.gson.JsonElement
import com.google.gson.JsonObject

class CalendarViewModel(
    private val calendarApiImpl: CalendarApiImpl,
    sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val accessToken = sharedPreferenceStorage.getInfo("access_token")

    private val _events = MutableLiveData<Map<String,String>>()
    val events : LiveData<Map<String,String>> get() = _events

    fun loadSchedules() {
        calendarApiImpl.scheduleApi(accessToken).subscribe({ response ->
            if(response.isSuccessful){
                parseEvents(response.body()!!)
            }
            else{
                Log.e("date",response.raw().toString())
            }
        }, {
            Log.e("date",it.toString())
        })
    }

    private fun parseEvents(body:JsonObject){
        for(month in 1..12){
            val monthEvents = body.getAsJsonObject("$month")
            val dates = monthEvents.keySet()
            Log.d("date",dates.toString())
        }
    }

}