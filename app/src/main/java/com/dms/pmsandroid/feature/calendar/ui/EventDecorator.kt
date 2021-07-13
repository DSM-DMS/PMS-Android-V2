package com.dms.pmsandroid.feature.calendar.ui

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDecorator(private val color: Int, private val date: String) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return "${day?.year}-${String.format("%02d",day?.month)}-${String.format("%02d",day?.day)}" == date
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(5F, color))
    }
}