package com.dms.pmsandroid.feature.meal

import android.os.Build
import androidx.annotation.RequiresApi
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import com.dms.pmsandroid.base.DatePickerDialog
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MealDatePickerDialog : DatePickerDialog() {
    override val vm: MealViewModel by inject()

    private val date by lazy {
        vm.date.value!!
    }

    override var year =
        date.substring(0, 4).toInt()


    override var month: Int =
        date.substring(4, 6).toInt()


    override var day: Int =
        date.substring(6, 8).toInt()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCompleteClicked() {
        val getDate = binding.dpYearNp.value.toString() + String.format(
            "%02d",
            binding.dpMonthNp.value
        ) + String.format("%02d", binding.dpDayNp.value)
        vm.date.value = getDate
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA)
        val calculateDate = LocalDate.parse(getDate, formatter)
        vm.weekDate.value = calculateDate.dayOfWeek.value
        vm.getMeal()
        dismiss()
    }

    override fun observeEvent() {
        vm.date.observe(viewLifecycleOwner,{
            day = it.substring(6, 8).toInt()
            month = it.substring(4, 6).toInt()
            year = it.substring(0,4).toInt()
        })
    }
}