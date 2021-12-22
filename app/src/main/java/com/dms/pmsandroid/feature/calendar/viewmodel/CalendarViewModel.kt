package com.dms.pmsandroid.feature.calendar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.base.SingleLiveEvent
import com.dms.pmsandroid.data.calendar.repository.CalendarRepository
import com.dms.pmsandroid.feature.calendar.model.EventModel
import com.dms.pmsandroid.feature.calendar.toCalendarDay
import com.prolificinteractive.materialcalendarview.CalendarDay

class CalendarViewModel(
    private val calendarRepository: CalendarRepository
) : ViewModel() {

    private val _events = MutableLiveData<MutableMap<CalendarDay, EventModel>>(HashMap())
    val events: LiveData<MutableMap<CalendarDay, EventModel>> get() = _events

    val selectedEventModel = MutableLiveData<EventModel>()

    val selectedDate = MutableLiveData(CalendarDay.today()) //CalendarDay 의 값은 한달씩 느립니다.

    val showingMonth = MutableLiveData(CalendarDay.today())

    val doneEventsSetting = SingleLiveEvent<Unit>()

    val updateCurrentDate = MutableLiveData(Event(false))

    fun nextMonth() {
        val nowMonth = showingMonth.value!!
        selectedDate.value = if(isDecember(nowMonth)) CalendarDay.from(nowMonth.year + 1, 1, 1)
        else CalendarDay.from(nowMonth.year, nowMonth.month + 1, nowMonth.day)
        updateCurrentDate.value = Event(true)
    }

    fun beforeMonth() {
        val nowMonth = showingMonth.value!!
        selectedDate.value = if (isFebruary(nowMonth)) CalendarDay.from(nowMonth.year - 1, 11, 1)
        else CalendarDay.from(nowMonth.year, nowMonth.month - 1, 1)
        updateCurrentDate.value = Event(true)
    }

    fun loadSchedules() {
        calendarRepository.getEvents().subscribe { result ->
            _events.value = events.value!!.apply {
                putAll(result.mapKeys { it.key.toCalendarDay() })
            }
            doneEventsSetting.call()
        }
    }

    private fun isDecember(nowMonth: CalendarDay): Boolean =
        nowMonth.month == 12

    private fun isFebruary(nowMonth: CalendarDay): Boolean =
        nowMonth.month == 1
}