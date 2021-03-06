package com.dms.pmsandroid.feature.calendar.ui.decorator

import android.content.Context
import android.text.style.ForegroundColorSpan
import com.dms.pmsandroid.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class DayDecorator(context: Context): DayViewDecorator {
    private val color = context.resources.getColor(R.color.black)
    override fun shouldDecorate(day: CalendarDay?): Boolean = true

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(ForegroundColorSpan(color))
    }
}