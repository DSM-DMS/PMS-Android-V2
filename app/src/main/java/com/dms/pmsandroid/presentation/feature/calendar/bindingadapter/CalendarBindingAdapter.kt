package com.dms.pmsandroid.presentation.feature.calendar.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dms.pmsandroid.R
import com.dms.pmsandroid.domain.calendar.entity.EventModel
import com.dms.pmsandroid.presentation.feature.calendar.toEmoji
import com.prolificinteractive.materialcalendarview.CalendarDay

@BindingAdapter("event_text")
fun TextView.eventText(event: EventModel?) {
    if(event != null && event.eventNames.isNotEmpty()) {
        var resultEventText = ""
        for (eventIndex in event.eventNames.indices) {
            resultEventText += "\n${event.eventTypes[eventIndex].toEmoji()} ${event.eventNames[eventIndex]}\n"
        }
        text = resultEventText
    } else {
        text = context.getString(R.string.empty_event)
    }
}

@BindingAdapter("calendar_date_text")
fun TextView.calendarDateText(calendarDay: CalendarDay?) {
    if(calendarDay != null) {
        val dateText = "${calendarDay.month + 1}월${calendarDay.day}일"
        text = dateText
    }
}

@BindingAdapter("calendar_month_text")
fun TextView.calendarMonthText(calendarDay: CalendarDay?) {
    if(calendarDay != null) {
        val dateText = "${calendarDay.year}년 ${calendarDay.month + 1}월"
        text = dateText
    }
}