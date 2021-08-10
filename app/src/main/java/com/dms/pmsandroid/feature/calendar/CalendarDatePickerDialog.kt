package com.dms.pmsandroid.feature.calendar

import android.widget.CalendarView
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.DatePickerDialog
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class CalendarDatePickerDialog(
    date: CalendarDay,
    override val vm: ViewModel,
    private val calendarView:MaterialCalendarView
) : DatePickerDialog() {

    override val day: Int = date.day
    override val year = date.year
    override val month = date.month

    override fun onCompleteClicked() {
        val result =
            CalendarDay.from(binding.dpYearNp.value, binding.dpMonthNp.value, binding.dpDayNp.value)
        calendarView.selectedDate = result
        calendarView.setDateSelected(result,true)
        dismiss()
    }
}