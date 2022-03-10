package com.dms.pmsandroid.data.calendar.repository

import com.dms.pmsandroid.data.calendar.remote.CalendarRemoteDatasource
import com.dms.pmsandroid.data.calendar.toEventModel
import com.dms.pmsandroid.data.calendar.toLocalDate
import com.dms.pmsandroid.data.local.room.EventDatabase
import com.dms.pmsandroid.data.local.room.RoomEvents
import com.dms.pmsandroid.domain.calendar.repository.CalendarRepository
import com.dms.pmsandroid.domain.calendar.entity.EventModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.collections.HashMap

class CalendarRepositoryImpl(
    private val calendarRemoteDatasource: CalendarRemoteDatasource,
    private val eventDatabase: EventDatabase
) : CalendarRepository {
    override fun getEvents(): Single<Map<LocalDate, EventModel>> =
        getSchedulesAtServerOrLocal()

    private fun getSchedulesAtServerOrLocal(): Single<Map<LocalDate, EventModel>> =
        calendarRemoteDatasource.getSchedules()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .flatMap { response ->
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!.toEventModel()
                    result.subscribe { event ->
                        CoroutineScope(Dispatchers.IO).launch {
                            eventDatabase.eventDao().run {
                                deleteEvents()
                                insertEvent(event.map {
                                    RoomEvents(
                                        it.key.toString(),
                                        it.value.eventNames
                                    )
                                })
                            }
                        }
                    }
                    return@flatMap result
                } else {
                    getSchedulesAtLocal()
                }
            }.onErrorResumeNext {
                getSchedulesAtLocal()
            }

    private fun getSchedulesAtLocal(): Single<Map<LocalDate, EventModel>> =
        eventDatabase.eventDao().getLocalEvent()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                val filteredList = it.filter { it.events.isNotEmpty() }
                HashMap<LocalDate, EventModel>().apply {
                    for (event in filteredList) {
                        put(event.date.toLocalDate(), event.events.toEventModel())
                    }
                }
            }
}