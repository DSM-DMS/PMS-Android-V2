package com.dms.pmsandroid.feature.calendar.ui.decorator

import android.annotation.SuppressLint
import android.content.Context
import com.dms.pmsandroid.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class SelectedDayDecorator(context: Context):DayViewDecorator  {
    private var date = CalendarDay.today()
    @SuppressLint("UseCompatLoadingForDrawables")
    val drawable = context.resources.getDrawable(R.drawable.shape_date_selected)
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }
    override fun decorate(view: DayViewFacade?) {
        view?.setBackgroundDrawable(drawable)
    }
}