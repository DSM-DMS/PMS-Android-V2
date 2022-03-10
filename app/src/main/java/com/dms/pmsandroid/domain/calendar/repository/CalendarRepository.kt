package com.dms.pmsandroid.domain.calendar.repository

import com.dms.pmsandroid.domain.calendar.entity.EventModel
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

interface CalendarRepository {
    fun getEvents(): Single<Map<LocalDate, EventModel>>
}