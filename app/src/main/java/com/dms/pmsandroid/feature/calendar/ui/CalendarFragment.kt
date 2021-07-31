package com.dms.pmsandroid.feature.calendar.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentCalendarBinding
import com.dms.pmsandroid.feature.calendar.model.EventKeyModel
import com.dms.pmsandroid.feature.calendar.ui.decorator.EventDecorator
import com.dms.pmsandroid.feature.calendar.ui.decorator.SaturdayDecorator
import com.dms.pmsandroid.feature.calendar.ui.decorator.SelectedDayDecorator
import com.dms.pmsandroid.feature.calendar.ui.decorator.SundayDecorator
import com.dms.pmsandroid.feature.calendar.viewmodel.CalendarViewModel
import com.dms.pmsandroid.ui.MainViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar),
    OnDateSelectedListener, OnMonthChangedListener {

    override val vm: CalendarViewModel by viewModel()
    private val mainVm: MainViewModel by inject()

    private val setMonth = arrayListOf(false,false,false,false,false,false,false,false,false,false,false,false)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCalendarView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun observeEvent() {
        mainVm.doneToken.observe(viewLifecycleOwner, {
            if (it) {
                vm.loadSchedules()
            }
        })
        vm.doneEventsSetting.observe(viewLifecycleOwner, {
            if (it) {
                initEventTv()
            }
        })

    }

    private fun initEventTv() {
        val currentDate = CalendarDay.today()
        setMonth[currentDate.month] = true
        val setDate = CalendarDay.from(currentDate.year, currentDate.month + 1, currentDate.day)
        val formatDate = formatDate(setDate)
        val eventKeys = vm.events.value!!.keys
        val decorators = ArrayList<EventDecorator>()
        for (key in eventKeys) {
            if (key.month == setDate.month) {
                decorators.add(EventDecorator(key.day, vm.events.value!![key]!!.eventSize))
            }
        }
        binding.calendarView.addDecorators(decorators)

        setEventTv(formatDate, setDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCalendarView() {
        binding.calendarEventTv.text = "일정을 읽어오는중입니다..."
        val calendarView = binding.calendarView
        val currentDate = CalendarDay.today()
        calendarView.run {
            addDecorators(
                SaturdayDecorator(),
                SundayDecorator(),
                SelectedDayDecorator(requireContext())
            )
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
        val setDate = CalendarDay.from(date.year, date.month + 1, date.day)
        val selectedDate = formatDate(setDate)
        setEventTv(selectedDate, setDate)
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
        val key = EventKeyModel(calendarDay.month, date)
        val event = vm.events.value?.get(key)?.eventName ?: "일정이 없습니다"
        with(binding) {
            calendarEventTv.text = event
            calendarDateTv.text = "${calendarDay.month}월${calendarDay.day}일"
        }
    }

    override fun onMonthChanged(widget: MaterialCalendarView?, date: CalendarDay?) {
        if(!setMonth[date?.month?:0]){
            val month = (date?.month ?: 0) + 1
            val eventKeys = vm.events.value!!.keys

            val decorators = ArrayList<EventDecorator>()
            for (key in eventKeys) {
                if (key.month == month) {
                    decorators.add(EventDecorator(key.day, vm.events.value!![key]!!.eventSize))
                }
            }
            binding.calendarView.addDecorators(decorators)
            setMonth[date?.month?:0] = true
        }

    }

}