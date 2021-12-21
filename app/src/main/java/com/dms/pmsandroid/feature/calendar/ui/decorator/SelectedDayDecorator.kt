package com.dms.pmsandroid.feature.calendar.ui.decorator

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.dms.pmsandroid.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class SelectedDayDecorator(context: Context):DayViewDecorator  {
    private var date = CalendarDay.today()
    val drawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.shape_date_selected)

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }

    override fun decorate(view: DayViewFacade?) {
        if(drawable != null) {
            view?.setBackgroundDrawable(drawable)
        }
    }
}