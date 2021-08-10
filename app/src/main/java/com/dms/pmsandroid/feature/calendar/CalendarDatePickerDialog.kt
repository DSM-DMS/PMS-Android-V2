package com.dms.pmsandroid.feature.calendar

import com.dms.pmsandroid.base.DatePickerDialog
import com.dms.pmsandroid.feature.calendar.viewmodel.CalendarViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class CalendarDatePickerDialog(
    override val vm: CalendarViewModel
) : DatePickerDialog() {

    private var date = vm.selectedDate.value

    override var day = date!!.day
    override var year = date!!.year
    override var month = date!!.month+1

    override fun onCompleteClicked() {
        val result =
            CalendarDay.from(binding.dpYearNp.value, binding.dpMonthNp.value-1, binding.dpDayNp.value)
        vm.selectedDate.value = result
        vm.updateCurrentDate.value = true
        dismiss()
    }

    override fun observeEvent() {
        vm.selectedDate.observe(viewLifecycleOwner,{
            day = it.day
            year = it.year
            month = it.month+1
        })
    }
}