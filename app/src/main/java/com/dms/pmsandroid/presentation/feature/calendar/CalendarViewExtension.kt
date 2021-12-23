package com.dms.pmsandroid.presentation.feature.calendar

import com.dms.pmsandroid.domain.calendar.entity.EventTypes
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.time.LocalDate

fun LocalDate.toCalendarDay() =
    CalendarDay.from(year, monthValue, dayOfMonth)

fun EventTypes.toEmoji(): String {
    return when (this) {
        EventTypes.MUST_GO_HOME -> {
            "🟢"
        }
        EventTypes.EXAM -> {
            "🟣"
        }
        EventTypes.VACATION -> {
            "🟡"
        }
        EventTypes.HOLIDAYS -> {
            "🔴"
        }
        EventTypes.ETC -> {
            "🔵"
        }
    }
}