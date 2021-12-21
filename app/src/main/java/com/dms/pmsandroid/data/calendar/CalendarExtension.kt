package com.dms.pmsandroid.data.calendar

import com.dms.pmsandroid.data.calendar.dto.CalendarResponse
import com.dms.pmsandroid.feature.calendar.model.EventModel
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
    LocalDate.of(this.substring(0,3).toInt(), this.substring(4,6).toInt(), this.substring(7,9).toInt())

fun List<String>.toEventModel(): EventModel {
    val eventTypes = ArrayList<EventTypes>()
    for(event in this) {
        eventTypes.add(event.toEventType())
    }
    return EventModel(this, eventTypes)
}

private fun Map<String, List<String>>.toEventModelMap() =
    this.mapKeys { it.key.toLocalDate() }.mapValues { it.value.toEventModel() }