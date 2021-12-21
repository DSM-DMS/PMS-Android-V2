package com.dms.pmsandroid.data.calendar.repository

import com.dms.pmsandroid.feature.calendar.model.EventModel
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

interface CalendarRepository {
    fun getEvents(): Single<Map<LocalDate, EventModel>>
}