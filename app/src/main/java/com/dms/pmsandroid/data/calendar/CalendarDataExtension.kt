package com.dms.pmsandroid.data.calendar

import com.dms.pmsandroid.data.calendar.dto.CalendarResponse
import com.dms.pmsandroid.domain.calendar.entity.EventModel
import com.dms.pmsandroid.domain.calendar.entity.EventTypes
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

fun CalendarResponse.toEventModel(): Single<MutableMap<LocalDate, EventModel>> = getEventsInResponse(this)

private fun getEventsInResponse(response: CalendarResponse): Single<MutableMap<LocalDate, EventModel>> =
    Single.just(HashMap<LocalDate, EventModel>().apply {
        putAll(response.janEvents.toEventModelMap())
        putAll(response.febEvents.toEventModelMap())
        putAll(response.marEvents.toEventModelMap())
        putAll(response.aprEvents.toEventModelMap())
        putAll(response.mayEvents.toEventModelMap())
        putAll(response.junEvents.toEventModelMap())
        putAll(response.julEvents.toEventModelMap())
        putAll(response.augEvents.toEventModelMap())
        putAll(response.sepEvents.toEventModelMap())
        putAll(response.octEvents.toEventModelMap())
        putAll(response.novEvents.toEventModelMap())
        putAll(response.decEvents.toEventModelMap())
    })

fun String.toLocalDate():LocalDate =
    LocalDate.of(this.substring(0,4).toInt(), this.substring(5,7).toInt(), this.substring(8,10).toInt())

fun List<String>.toEventModel(): EventModel {
    val eventTypes = ArrayList<EventTypes>()
    val resultEvents = ArrayList<String>()
    resultEvents.addAll(this)
    resultEvents.removeIf { it == "토요휴업일" }
    for(event in resultEvents) {
        eventTypes.add(event.toEventType())
    }
    return EventModel(resultEvents, eventTypes)
}

private fun Map<String, List<String>>.toEventModelMap() =
    this.mapKeys { it.key.toLocalDate() }.mapValues { it.value.toEventModel() }

fun String.toEventType(): EventTypes {
    return when (this) {
        "의무귀가" -> {
            EventTypes.MUST_GO_HOME
        }

        "중간고사", "기말고사" -> {
            EventTypes.EXAM
        }

        "여름방학", "겨울방학", "여름방학식", "겨울방학식" -> {
            EventTypes.VACATION
        }

        "신정", "어린이날", "석가탄신일", "현충일", "광복절", "대체공휴일", "추석연휴", "추석", "개천절", "한글날", "기독탄신일(성탄절)" -> {
            EventTypes.HOLIDAYS
        }

        else -> {
            EventTypes.ETC
        }
    }
}