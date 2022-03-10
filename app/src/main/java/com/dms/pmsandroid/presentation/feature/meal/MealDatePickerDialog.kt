package com.dms.pmsandroid.presentation.feature.meal

import android.os.Build
import androidx.annotation.RequiresApi
import com.dms.pmsandroid.presentation.feature.meal.viewmodel.MealViewModel
import com.dms.pmsandroid.presentation.base.DatePickerDialog
import org.koin.android.ext.android.inject
import java.time.LocalDate

class MealDatePickerDialog : DatePickerDialog() {
    override val vm: MealViewModel by inject()

    private val date by lazy {
        vm.selectedDate.value!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override var year =
        date.year


    @RequiresApi(Build.VERSION_CODES.O)
    override var month: Int =
        date.monthValue


    @RequiresApi(Build.VERSION_CODES.O)
    override var day: Int =
        date.dayOfMonth


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCompleteClicked() {
        vm.meals.value!!.clear()
        vm.selectedDate.value = LocalDate.of(binding.dpYearNp.value, binding.dpMonthNp.value, binding.dpDayNp.value)
        vm.currentPosition.value = Int.MAX_VALUE / 2
        vm.getInitMeal()
        dismiss()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun observeEvent() {
        vm.selectedDate.observe(viewLifecycleOwner,{
            day = it.dayOfMonth
            month = it.monthValue
            year = it.year
        })
    }
}