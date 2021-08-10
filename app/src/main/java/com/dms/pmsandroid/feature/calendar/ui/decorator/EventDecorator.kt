package com.dms.pmsandroid.feature.calendar.ui.decorator

import android.content.Context
import android.graphics.Color
import android.text.style.ForegroundColorSpan
import androidx.core.content.contentValuesOf
import com.dms.pmsandroid.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator(private val date: String,private val dots:List<Int>,private val context: Context) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        val setDay = CalendarDay.from(day?.year?:0,(day?.month?:-1)+1,day?.day?:0)
        return "${setDay.year}-${String.format("%02d",setDay.month)}-${String.format("%02d",setDay.day)}" == date
    }

    private val color = context.resources.getColor(R.color.red_200)
    override fun decorate(view: DayViewFacade) {
        if(dots.contains(Color.RED)){
            view.addSpan(ForegroundColorSpan(color))
        }
        view.addSpan(CustomMultipleDotSpan(dots, context))
    }
}