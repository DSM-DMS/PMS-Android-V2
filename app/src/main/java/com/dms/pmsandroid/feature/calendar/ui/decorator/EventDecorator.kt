package com.dms.pmsandroid.feature.calendar.ui.decorator

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator(private val date: String,private val dots:Int) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        val setDay = CalendarDay.from(day?.year?:0,(day?.month?:-1)+1,day?.day?:0)
        return "${setDay.year}-${String.format("%02d",setDay.month)}-${String.format("%02d",setDay.day)}" == date
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(CustomMultipleDotSpan(dots))
    }
}