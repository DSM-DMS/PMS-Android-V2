package com.dms.pmsandroid.feature.meal

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogDatePickerBinding
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import com.dms.pmsandroid.ui.DatePickerDialog
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MealDatePickerDialog : DatePickerDialog() {
    override val vm: MealViewModel by inject()

    private val date by lazy {
        vm.date.value!!
    }

    override val year: Int
            by lazy {
                date.substring(0, 4).toInt()
            }

    override val month: Int
        get() = TODO("Not yet implemented")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val year = date!!
        val month = date.substring(4, 6).toInt()
        val day = date.substring(6, 8).toInt()

        binding.run {
            dpYearNp.run {
                descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                wrapSelectorWheel = false
                maxValue = year + 1
                minValue = year - 1
                value = year
            }
            dpMonthNp.run {
                descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                wrapSelectorWheel = false
                maxValue = 12
                minValue = 1
                value = month
            }
            dpDayNp.run {
                descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                wrapSelectorWheel = false
                maxValue = 31
                minValue = 1
                value = day
            }

            dpCompleteTv.setOnClickListener {
                val getDate = dpYearNp.value.toString() + String.format(
                    "%02d",
                    dpMonthNp.value
                ) + String.format("%02d", dpDayNp.value)
                vm.date.value = getDate
                val formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA)
                val calculateDate = LocalDate.parse(getDate, formatter)
                vm.weekDate.value = calculateDate.dayOfWeek.value
                vm.getMeal()
                dismiss()
            }
            dpCancelTv.setOnClickListener {
                dismiss()
            }
        }
    }

}