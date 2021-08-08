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
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MealDatePickerDialog : BaseDialog<DialogDatePickerBinding>(R.layout.dialog_date_picker) {
    override val vm: MealViewModel by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = vm.date.value
        val year = date!!.substring(0, 4).toInt()
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