package com.dms.pmsandroid.data.calendar.repository

import com.dms.pmsandroid.data.calendar.remote.ProvideCalendarApi
import com.dms.pmsandroid.data.calendar.toEventModel
import com.dms.pmsandroid.data.calendar.toEventType
import com.dms.pmsandroid.data.calendar.toLocalDate
import com.dms.pmsandroid.data.local.room.EventDatabase
import com.dms.pmsandroid.feature.calendar.model.EventKeyModel
import com.dms.pmsandroid.feature.calendar.model.EventModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashMap

class CalendarRepositoryImpl(
    private val provideCalendarApi: ProvideCalendarApi,
    private val eventDatabase: EventDatabase
): CalendarRepository {
    override fun getEvents(): Single<Map<LocalDate, EventModel>> =
        getSchedulesAtServerOrLocal()

    private fun getSchedulesAtServerOrLocal(): Single<Map<LocalDate, EventModel>> =
        provideCalendarApi.getSchedules()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .flatMap { response ->
                if(response.isSuccessful&&response.body() != null) {
                     response.body()!!.toEventModel()
                } else {
                     getSchedulesAtLocal()
                }
            }

    private fun getSchedulesAtLocal(): Single<MutableMap<LocalDate, EventModel>> =
        eventDatabase.eventDao().getLocalEvent().map{
            HashMap<LocalDate, EventModel>().apply {
                for(event in it) {
                    put(event.date.toLocalDate(), event.events.toEventModel())
                }
            }
        }

}