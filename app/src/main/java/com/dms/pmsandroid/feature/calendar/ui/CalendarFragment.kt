package com.dms.pmsandroid.feature.calendar.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.base.EventObserver
import com.dms.pmsandroid.databinding.FragmentCalendarBinding
import com.dms.pmsandroid.feature.calendar.CalendarDatePickerDialog
import com.dms.pmsandroid.feature.calendar.model.EventKeyModel
import com.dms.pmsandroid.feature.calendar.ui.decorator.*
import com.dms.pmsandroid.feature.calendar.viewmodel.CalendarViewModel
import com.dms.pmsandroid.ui.MainViewModel
import com.jakewharton.rxbinding4.view.clicks
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar),
    OnDateSelectedListener, OnMonthChangedListener {

    override val vm: CalendarViewModel by viewModel()
    private val mainVm: MainViewModel by inject()

    private val setMonth = HashMap<Int, Boolean>()

    private val helperDialog by lazy {
        CalendarHelperDialog(vm)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCalendarView()
        vm.loadLocalEvents()
        binding.calendarHelperTv.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
            helperDialog.show(requireActivity().supportFragmentManager, "HelperDialog")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun observeEvent() {
        mainVm.doneToken.observe(viewLifecycleOwner, {
            if (it) {
                vm.loadSchedules()
            }
        })
        mainVm.connectedInternet.observe(viewLifecycleOwner,{
            if(!it){
                vm.loadLocalEvents()
            }
        })
        vm.run {
            doneEventsSetting.observe(viewLifecycleOwner, {
                if (it) {
                    initEventTv()
                }
            })
            selectedDate.observe(viewLifecycleOwner, {
                setMonthTv(it)
                val plusMonth = CalendarDay.from(it.year, it.month + 1, it.day)
                val formedDate = formatDate(plusMonth)
                setEventTv(formedDate, it)
            })
            updateCurrentDate.observe(viewLifecycleOwner, EventObserver {
                val date = vm.selectedDate.value
                binding.calendarView.currentDate = date
                binding.calendarView.selectedDate = date
            })
        }

    }

    private fun initEventTv() {
        val currentDate = CalendarDay.today()

        loadEvents(currentDate.month + 1)

        vm.selectedDate.value = currentDate
    }

    private fun loadEvents(month: Int) {
        val events = vm.events.value ?: return
        val key = events.keys
        val decorators = ArrayList<EventDecorator>()

        setMonth[month] = true
        setMonth[month + 1] = true

        Observable.fromIterable(key).filter { k -> k.month == month || k.month == month + 1 }
            .filter { v -> events[v] != null }
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(
                Schedulers.io()
            ).subscribe({ k ->
                decorators.add(
                    EventDecorator(
                        k.day,
                        events[k]!!.dotTypes,
                        binding.calendarView.context
                    )
                )
            }, {

            }, {
                binding.calendarView.addDecorators(decorators)
                binding.calendarShimmerContainer.hideShimmer()
            })
    }


    private val calendarDatePickerDialog by lazy {
        CalendarDatePickerDialog(vm)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCalendarView() {
        binding.calendarShimmerContainer.startShimmer()
        binding.calendarEventTv.text = "\n일정을 읽어오는중입니다...\n"
        val calendarView = binding.calendarView
        val currentDate = CalendarDay.today()
        vm.selectedDate.value = currentDate
        vm.updateCurrentDate.value = Event(true)
        setMonthTv(currentDate)
        calendarView.run {
            addDecorators(
                DayDecorator(requireContext()),
                SaturdayDecorator(requireContext()),
                SundayDecorator(requireContext()),
                SelectedDayDecorator(requireContext())
            )
            topbarVisible = false
            setWeekDayTextAppearance(R.style.saturdayColor)
            setDateSelected(currentDate, true)
            setOnDateChangedListener(this@CalendarFragment)
            setOnMonthChangedListener(this@CalendarFragment)
        }
        binding.calendarMonthTv.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
            calendarDatePickerDialog.show(
                requireActivity().supportFragmentManager,
                "CalendarDatePickerDialog"
            )
        }
    }

    override fun onDateSelected(
        widget: MaterialCalendarView,
        date: CalendarDay,
        selected: Boolean
    ) {
        vm.selectedDate.value = date
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
        val key = EventKeyModel(calendarDay.month + 1, date)
        val event = vm.events.value?.get(key)?.eventName ?: "\n일정이 없습니다\n"
        with(binding) {
            calendarEventTv.text = event
            calendarDateTv.text = "${calendarDay.month + 1}월${calendarDay.day}일"
        }
    }

    override fun onMonthChanged(widget: MaterialCalendarView?, date: CalendarDay?) {
        setMonthTv(date)

        val month = (date?.month ?: 0) + 1
        if (setMonth[month] != true) {
            loadEvents(month)
        }
        if (setMonth[month + 1] != true) {
            loadEvents(month + 1)
        }
    }

    private fun setMonthTv(date: CalendarDay?) {
        binding.calendarMonthTv.text = "${date?.year}년 ${((date?.month) ?: 0) + 1}월"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setMonth.clear()
    }

}