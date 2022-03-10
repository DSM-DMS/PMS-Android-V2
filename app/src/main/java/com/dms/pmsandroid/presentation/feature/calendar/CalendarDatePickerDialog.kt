package com.dms.pmsandroid.presentation.feature.calendar

import com.dms.pmsandroid.presentation.base.DatePickerDialog
import com.dms.pmsandroid.presentation.base.Event
import com.dms.pmsandroid.presentation.feature.calendar.viewmodel.CalendarViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay

class CalendarDatePickerDialog(
    override val vm: CalendarViewModel
) : DatePickerDialog() {

    private var date = vm.selectedDate.value

    override var day = date!!.day
    override var year = date!!.year
    override var month = date!!.month + 1

    override fun onCompleteClicked() {
        val result =
            CalendarDay.from(
                binding.dpYearNp.value,
                binding.dpMonthNp.value - 1,
                binding.dpDayNp.value
            )
        vm.selectedDate.value = result
        vm.updateCurrentDate.value = Event(true)
        dismiss()
    }

    override fun observeEvent() {
        vm.selectedDate.observe(viewLifecycleOwner, {
            day = it.day
            year = it.year
            month = it.month + 1
        })
    }
}