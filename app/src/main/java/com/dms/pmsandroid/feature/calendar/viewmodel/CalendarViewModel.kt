package com.dms.pmsandroid.feature.calendar.viewmodel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.base.SingleLiveEvent
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.local.room.EventDatabase
import com.dms.pmsandroid.data.local.room.RoomEvents
import com.dms.pmsandroid.data.remote.calendar.CalendarApiImpl
import com.dms.pmsandroid.feature.calendar.model.EventKeyModel
import com.dms.pmsandroid.feature.calendar.model.EventModel
import com.google.gson.JsonObject
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalendarViewModel(
    private val calendarApiImpl: CalendarApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val eventDatabase: EventDatabase
) : ViewModel() {

    private val _events = MutableLiveData<MutableMap<EventKeyModel, EventModel>>(HashMap())
    val events: LiveData<MutableMap<EventKeyModel, EventModel>> get() = _events

    val selectedDate = MutableLiveData<CalendarDay>()

    val doneEventsSetting = SingleLiveEvent<Boolean>()

    val updateCurrentDate = MutableLiveData(Event(false))

    fun nextMonth() {
        val setDate = selectedDate.value
        selectedDate.value = if (setDate!!.month > 10) CalendarDay.from(setDate.year + 1, 0, 1)
        else CalendarDay.from(setDate.year, setDate.month + 1, 1)
        updateCurrentDate.value = Event(true)
    }

    fun beforeMonth() {
        val setDate = selectedDate.value
        selectedDate.value = if (setDate!!.month < 1) CalendarDay.from(setDate.year - 1, 11, 1)
        else CalendarDay.from(setDate.year, setDate.month - 1, 1)
        updateCurrentDate.value = Event(true)
    }

    fun loadSchedules() {


        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        calendarApiImpl.scheduleApi(accessToken).subscribe { response ->
            if (response.isSuccessful) {
                CoroutineScope(Dispatchers.IO).launch {
                    eventDatabase.eventDao().deleteEvents()
                }
                parseEvents(response.body()!!)

            }
        }
    }

    fun loadLocalEvents() {
        eventDatabase.eventDao().getLocalEvent().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe { events ->
                for (event in events) {
                    val month = event.date.substring(4, 6).toInt()+1
                    val eventKey = EventKeyModel(month, event.date)
                    val dotTypes = event.dot.split(",").map { it.trim() }
                    val eventModel = EventModel(event.event, dotTypes as ArrayList<Int>)
                    _events.value!![eventKey] = eventModel
                }
                doneEventsSetting.value = true
            }
    }

    private fun parseEvents(body: JsonObject) {
        val eventsList = ArrayList<RoomEvents>()
        for (month in 1..12) {
            val monthEvents = body.getAsJsonObject("$month")
            val dates = monthEvents.keySet()
            var date = ""

            Observable.fromIterable(dates).map { event ->
                date = event
                monthEvents.getAsJsonArray(event)
            }
                .filter { event -> event.size() > 0 }
                .switchMap {
                    return@switchMap Observable.fromArray(it)
                }
                .map {
                    var eventName = ""
                    val dotTypes = ArrayList<Int>()
                    for (pos in 0 until it.size()) {
                        val event = it[pos].toString().substring(1, it[pos].toString().length - 1)
                        when (event) {
                            "토요휴업일" -> {
                            }

                            "의무귀가" -> {
                                dotTypes.add(Color.GREEN)
                                eventName += addHomeDot()
                            }

                            "중간고사", "기말고사" -> {
                                dotTypes.add(Color.BLACK)
                                eventName += addExamDot(event)
                            }

                            "여름방학", "겨울방학", "여름방학식", "겨울방학식" -> {
                                dotTypes.add(Color.YELLOW)
                                eventName += addYellowDot(event)
                            }

                            "신정", "어린이날", "석가탄신일", "현충일", "광복절", "대체공휴일", "추석연휴", "추석", "개천절", "한글날", "기독탄신일(성탄절)" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot(event)
                            }

                            "재량휴업" -> {
                                dotTypes.add(Color.GRAY)
                                eventName += addRedDot(event)
                            }

                            else -> {
                                dotTypes.add(Color.BLUE)
                                eventName += addBlueDot(event)
                            }
                        }
                    }
                    return@map EventModel(eventName, dotTypes)
                }.filter { event -> event.eventName.isNotEmpty() }.subscribe { eventName ->
                    val key = EventKeyModel(month, date)
                    _events.value!![key] = eventName
                    val dotTypes = eventName.dotTypes.joinToString()
                    val event = RoomEvents(date, eventName.eventName, dotTypes)
                    eventsList.add(event)
                }
        }
        CoroutineScope(Dispatchers.IO).launch {
            eventDatabase.eventDao().insertEvent(eventsList)
        }
        doneEventsSetting.value = true
    }

    private fun addRedDot(eventName: String): String {
        return "\n🔴 $eventName\n"
    }

    private fun addYellowDot(eventName: String): String {
        return "\n🟡 $eventName\n"
    }

    private fun addHomeDot(): String {
        return "\n🏠 의무귀가\n"
    }

    private fun addExamDot(eventName: String): String {
        return "\n🖍 $eventName\n"
    }

    private fun addBlueDot(eventName: String): String {
        return "\n🔵 $eventName\n"
    }

}