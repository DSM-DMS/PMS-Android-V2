package com.dms.pmsandroid.feature.calendar

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.time.LocalDate

fun LocalDate.toCalendarDay() =
    CalendarDay.from(year, monthValue, dayOfMonth)