package com.dms.pmsandroid.feature.calendar.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentCalendarBinding
import com.dms.pmsandroid.feature.calendar.ui.decorator.EventDecorator
import com.dms.pmsandroid.feature.calendar.ui.decorator.SaturdayDecorator
import com.dms.pmsandroid.feature.calendar.viewmodel.CalendarViewModel
import com.dms.pmsandroid.ui.MainViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar),
    OnDateSelectedListener,OnMonthChangedListener {

    override val vm: CalendarViewModel by viewModel()
    private val mainVm: MainViewModel by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarEventTv.text = "일정을 읽어오는중입니다..."
        setCalendarView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun observeEvent() {
        mainVm.doneToken.observe(viewLifecycleOwner, {
            if (it) {
                vm.loadSchedules()
            }
        })
        vm.doneEventsSetting.observe(viewLifecycleOwner,{
            if(it){
                initEventTv()
            }
        })

    }

    private fun initEventTv(){
        val currentDate = CalendarDay.today()
        val formatDate = formatDate(currentDate)
        setEventTv(formatDate,currentDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCalendarView() {
        val calendarView = binding.calendarView
        val currentDate = CalendarDay.today()
        calendarView.run{
            addDecorators(SaturdayDecorator())
            setWeekDayTextAppearance(R.style.saturdayColor)
            setDateSelected(currentDate, true)
            setOnDateChangedListener(this@CalendarFragment)
            setOnMonthChangedListener(this@CalendarFragment)
        }
    }

    override fun onDateSelected(
        widget: MaterialCalendarView,
        date: CalendarDay,
        selected: Boolean
    ) {
        val selectedDate = formatDate(date)
        setEventTv(selectedDate,date)
    }

    private fun formatDate(date: CalendarDay): String {
        val month = if (date.month < 10) {
            "0${date.month}"
        } else {
            date.month
        }

        val day = if (date.day < 10) {
            "0${date.day}"
        } else {
            date.day
        }
        return "${date.year}-$month-$day"
    }

    @SuppressLint("SetTextI18n")
    private fun setEventTv(date: String, calendarDay: CalendarDay) {
        val event = vm.events.value?.get(date) ?: "일정이 없습니다"
        with(binding){
            calendarEventTv.text = event
            calendarDateTv.text = "${calendarDay.month}월${calendarDay.day}일"
        }
    }

    override fun onMonthChanged(widget: MaterialCalendarView?, date: CalendarDay?) {

    }

}