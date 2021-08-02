package com.dms.pmsandroid.feature.calendar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.calendar.CalendarApiImpl
import com.dms.pmsandroid.feature.calendar.model.EventKeyModel
import com.dms.pmsandroid.feature.calendar.model.EventModel
import com.google.gson.JsonObject

class CalendarViewModel(
    private val calendarApiImpl: CalendarApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _events = MutableLiveData<MutableMap<EventKeyModel, EventModel>>(HashMap())
    val events: LiveData<MutableMap<EventKeyModel, EventModel>> get() = _events

    val doneEventsSetting = MutableLiveData(false)

    fun loadSchedules() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        calendarApiImpl.scheduleApi(accessToken).subscribe { response ->
            if (response.isSuccessful) {
                parseEvents(response.body()!!)
            }
        }
    }

    private val dots = arrayListOf("🔵","🔴","🟢","🟡","🟣","🟠")

    private fun parseEvents(body: JsonObject) {
        for (month in 1..12) {
            val monthEvents = body.getAsJsonObject("$month")
            val dates = monthEvents.keySet()
            for (date in dates) {
                var dotsIndex = 0
                var eventName = "${dots[dotsIndex]}  "
                dotsIndex+=1
                val events = monthEvents.getAsJsonArray(date)
                eventName += events[0].toString().substring(1, events[0].toString().length - 1)
                if (events.size() > 1) {
                    for (pos in 1 until events.size()) {
                        eventName += "\n\n${dots[dotsIndex]}  ${
                            events[pos].toString().substring(1, events[pos].toString().length - 1)
                        }"
                        dotsIndex+=1
                    }
                }
                val key = EventKeyModel(month,date)
                _events.value!![key] = EventModel(eventName,dotsIndex)
            }
        }
        doneEventsSetting.value = true
    }

}