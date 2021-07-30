package com.dms.pmsandroid.feature.calendar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.calendar.CalendarApiImpl
import com.google.gson.JsonObject

class CalendarViewModel(
    private val calendarApiImpl: CalendarApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _events = MutableLiveData<MutableMap<String, String>>(HashMap())
    val events: LiveData<MutableMap<String, String>> get() = _events

    val doneEventsSetting = MutableLiveData(false)

    fun loadSchedules() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        calendarApiImpl.scheduleApi(accessToken).subscribe { response ->
            if (response.isSuccessful) {
                parseEvents(response.body()!!)
            }
        }
    }

    private fun parseEvents(body: JsonObject) {
        for (month in 1..12) {
            val monthEvents = body.getAsJsonObject("$month")
            val dates = monthEvents.keySet()
            for (date in dates) {
                var eventName = "🔵  "
                val events = monthEvents.getAsJsonArray(date)
                eventName += events[0].toString().substring(1, events[0].toString().length - 1)
                if (events.size() > 1) {
                    for (pos in 1 until events.size()) {
                        eventName += "\n\n🔵  ${
                            events[pos].toString().substring(1, events[0].toString().length - 1)
                        }"
                    }
                }
                _events.value!![date] = eventName
            }
            doneEventsSetting.value = true
        }
    }

}