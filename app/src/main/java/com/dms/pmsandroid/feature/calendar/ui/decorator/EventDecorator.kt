package com.dms.pmsandroid.feature.calendar.ui.decorator

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator(private val date: String) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return "${day?.year}-${String.format("%02d",day?.month)}-${String.format("%02d",day?.day)}" == date
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(CustomMultipleDotSpan())
    }
}