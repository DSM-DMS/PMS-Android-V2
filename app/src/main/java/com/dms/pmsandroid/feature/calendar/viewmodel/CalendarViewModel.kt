package com.dms.pmsandroid.feature.calendar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.calendar.CalendarApiImpl
import com.dms.pmsandroid.feature.calendar.model.EventKeyModel
import com.dms.pmsandroid.feature.calendar.model.EventModel
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable

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

    private val dots = arrayListOf("🟢", "🔴", "🔵", "🟡", "🟣", "🟠")

    private fun parseEvents(body: JsonObject) {
        for (month in 1..12) {
            val monthEvents = body.getAsJsonObject("$month")
            val dates = monthEvents.keySet()
            var date = ""
            var dotsIndex = 0
            Observable.fromIterable(dates).map { event ->
                date = event
                dotsIndex = 0
                monthEvents.getAsJsonArray(event)
            }
                .filter { event -> event.size() > 0 }
                .switchMap {
                    return@switchMap Observable.fromArray(it)
                }
                .map {
                    var eventName = ""
                    for (pos in 0 until it.size()) {
                        eventName += "\n${dots[dotsIndex]}  ${
                            it[pos].toString().substring(1, it[pos].toString().length - 1)
                        }\n"
                        dotsIndex += 1
                    }
                    return@map eventName
                }.subscribe({ eventName ->
                    val key = EventKeyModel(month, date)
                    _events.value!![key] = EventModel(eventName, dotsIndex)
                }, {}, {})
        }
        doneEventsSetting.value = true
    }

}