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

    private val dotTypes = ArrayList<Int>()

    private fun parseEvents(body: JsonObject) {
        for (month in 1..12) {
            val monthEvents = body.getAsJsonObject("$month")
            val dates = monthEvents.keySet()
            var date = ""

            Observable.fromIterable(dates).map { event ->
                date = event
                dotTypes.clear()
                monthEvents.getAsJsonArray(event)
            }
                .filter { event -> event.size() > 0 }
                .switchMap {
                    return@switchMap Observable.fromArray(it)
                }
                .map {
                    var eventName = ""
                    for (pos in 0 until it.size()) {
                        val event = it[pos].toString().substring(1, it[pos].toString().length - 1)
                        when (event) {
                            "토요휴업일" -> {

                            }

                            "의무귀가" -> {
                                eventName += addHomeDot()
                            }

                            "중간고사" -> {
                                eventName += addExamDot("중간고사")
                            }
                            "기말고사" -> {
                                eventName += addExamDot("기말고사")
                            }

                            "여름방학" -> {
                                eventName += addYellowDot("여름방학")
                            }
                            "겨울방학" -> {
                                eventName += addYellowDot("겨울방학")
                            }
                            "여름방학식" -> {
                                eventName += addYellowDot("여름방학식")
                            }
                            "겨울방학식" -> {
                                eventName += addYellowDot("겨울방학식")
                            }

                            "신정" -> {
                                eventName += addRedDot("신정")
                            }
                            "어린이날" -> {
                                eventName += addRedDot("어린이날")
                            }
                            "석가탄신일" -> {
                                eventName += addRedDot("석가탄신일")
                            }
                            "현충일" -> {
                                eventName += addRedDot("현충일")
                            }
                            "광복절" -> {
                                eventName += addRedDot("광복절")
                            }
                            "대체공휴일" -> {
                                eventName += addRedDot("대체공휴일")
                            }
                            "재량휴업" -> {
                                eventName += addRedDot("재량휴업")
                            }
                            "추석연휴" -> {
                                eventName += addRedDot("추석연휴")
                            }
                            "추석" -> {
                                eventName += addRedDot("추석")
                            }
                            "개천절" -> {
                                eventName += addRedDot("개천절")
                            }
                            "한글날" -> {
                                eventName += addRedDot("한글날")
                            }
                            "기독탄신일(성탄절)" -> {
                                eventName += addRedDot("기독탄신일(성탄절)")
                            }
                            else -> {
                                eventName += addBlueDot(event)
                            }
                        }
                    }
                    return@map eventName
                }.filter { event -> event.isNotEmpty() }.subscribe({ eventName ->
                    val key = EventKeyModel(month, date)
                    _events.value!![key] = EventModel(eventName, dotTypes)
                }, {}, {})
        }
        doneEventsSetting.value = true
    }

    private fun addRedDot(eventName: String): String {
        dotTypes.add(Color.RED)
        return "\n🔴 $eventName\n"
    }

    private fun addYellowDot(eventName: String): String {
        dotTypes.add(Color.YELLOW)
        return "\n🟡 $eventName\n"
    }

    private fun addHomeDot(): String {
        dotTypes.add(Color.GREEN)
        return "\n🏠 의무귀가\n"
    }

    private fun addExamDot(eventName: String): String {
        dotTypes.add(123)
        return "\n🖍 $eventName\n"
    }

    private fun addBlueDot(eventName:String):String{
        dotTypes.add(Color.BLUE)
        return "\n🔵 $eventName\n"
    }

}