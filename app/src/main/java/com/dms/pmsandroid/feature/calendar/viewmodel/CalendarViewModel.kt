package com.dms.pmsandroid.feature.calendar.viewmodel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.calendar.CalendarApiImpl
import com.dms.pmsandroid.feature.calendar.model.EventKeyModel
import com.dms.pmsandroid.feature.calendar.model.EventModel
import com.google.gson.JsonObject
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.reactivex.rxjava3.core.Observable

class CalendarViewModel(
    private val calendarApiImpl: CalendarApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _events = MutableLiveData<MutableMap<EventKeyModel, EventModel>>(HashMap())
    val events: LiveData<MutableMap<EventKeyModel, EventModel>> get() = _events

    val selectedDate = MutableLiveData<CalendarDay>()

    val doneEventsSetting = MutableLiveData(false)

    fun nextMonth() {
        val setDate = selectedDate.value
        selectedDate.value = if (setDate!!.month > 11) CalendarDay.from(setDate.year + 1, 0, 1)
        else CalendarDay.from(setDate.year, setDate.month + 1, 1)
    }

    fun beforeMonth(){
        val setDate = selectedDate.value
        selectedDate.value = if (setDate!!.month<1) CalendarDay.from(setDate.year-1,11,1)
        else CalendarDay.from(setDate.year,setDate.month-1,1)
    }

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

                            "중간고사" -> {
                                dotTypes.add(Color.BLACK)
                                eventName += addExamDot("중간고사")
                            }
                            "기말고사" -> {
                                dotTypes.add(Color.BLACK)
                                eventName += addExamDot("기말고사")
                            }

                            "여름방학" -> {
                                dotTypes.add(Color.YELLOW)
                                eventName += addYellowDot("여름방학")
                            }
                            "겨울방학" -> {
                                dotTypes.add(Color.YELLOW)
                                eventName += addYellowDot("겨울방학")
                            }
                            "여름방학식" -> {
                                dotTypes.add(Color.YELLOW)
                                eventName += addYellowDot("여름방학식")
                            }
                            "겨울방학식" -> {
                                dotTypes.add(Color.YELLOW)
                                eventName += addYellowDot("겨울방학식")
                            }

                            "신정" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("신정")
                            }
                            "어린이날" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("어린이날")
                            }
                            "석가탄신일" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("석가탄신일")
                            }
                            "현충일" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("현충일")
                            }
                            "광복절" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("광복절")
                            }
                            "대체공휴일" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("대체공휴일")
                            }
                            "재량휴업" -> {
                                dotTypes.add(Color.GRAY)
                                eventName += addRedDot("재량휴업")
                            }
                            "추석연휴" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("추석연휴")
                            }
                            "추석" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("추석")
                            }
                            "개천절" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("개천절")
                            }
                            "한글날" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("한글날")
                            }
                            "기독탄신일(성탄절)" -> {
                                dotTypes.add(Color.RED)
                                eventName += addRedDot("기독탄신일(성탄절)")
                            }
                            else -> {
                                dotTypes.add(Color.BLUE)
                                eventName += addBlueDot(event)
                            }
                        }
                    }
                    return@map EventModel(eventName, dotTypes)
                }.filter { event -> event.eventName.isNotEmpty() }.subscribe({ eventName ->
                    val key = EventKeyModel(month, date)
                    _events.value!![key] = eventName

                }, {}, {})
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