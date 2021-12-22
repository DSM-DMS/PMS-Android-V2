package com.dms.pmsandroid

import com.dms.pmsandroid.data.calendar.toLocalDate
import com.dms.pmsandroid.feature.calendar.toCalendarDay
import com.prolificinteractive.materialcalendarview.CalendarDay
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import java.time.LocalDate

class CalendarTest {
    private val testDate = LocalDate.now()

    private val testCalendarDay = CalendarDay.today()

    @Test
    fun `LocalDateToCalendarDay 성공`() {
        println(testDate.toCalendarDay())
        println(testCalendarDay)
        assert(testDate.toCalendarDay() == testCalendarDay)
    }

    @Test
    fun `StringToLocalDate() 성공`() {
        val dateString = "2021-12-21"
        println("${dateString.substring(0,4)} ${dateString.substring(5,7)} ${dateString.substring(8,10)}")
    }
}