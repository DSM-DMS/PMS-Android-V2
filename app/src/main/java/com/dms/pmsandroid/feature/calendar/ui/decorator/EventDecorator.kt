package com.dms.pmsandroid.feature.calendar.ui.decorator

import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.dms.pmsandroid.R
import com.dms.pmsandroid.data.calendar.EventTypes
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator(private val date: CalendarDay, private val eventTypes: List<EventTypes>, private val context: Context) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        val setDay = CalendarDay.from(day?.year?:0,(day?.month?:-1)+1,day?.day?:0)
        return setDay == date
    }

    private val red = ContextCompat.getColor(context, R.color.red_200)
    override fun decorate(view: DayViewFacade) {
        if(eventTypes.contains(EventTypes.HOLIDAYS)){
            view.addSpan(ForegroundColorSpan(red))
        }
        view.addSpan(CustomMultipleDotSpan(eventTypes, context))
    }
}