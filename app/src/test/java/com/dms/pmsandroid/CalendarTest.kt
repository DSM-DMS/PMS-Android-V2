package com.dms.pmsandroid

import com.dms.pmsandroid.feature.calendar.toCalendarDay
import com.prolificinteractive.materialcalendarview.CalendarDay
import org.junit.Test
import org.mockito.Mockito.`when`
import java.time.LocalDate

class CalendarTest {
    private val testDate = LocalDate.now()

    private val testCalendarDay = CalendarDay.today()

    @Test
    fun `LocalDateToCalendarDay 성공`() {
        `when`(testDate.toCalendarDay())
            .thenReturn(testCalendarDay)
    }
}