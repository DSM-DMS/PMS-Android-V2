package com.dms.pmsandroid.feature.meal

import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogDatePickerBinding
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MealDatePickerDialog : BaseDialog<DialogDatePickerBinding>(R.layout.dialog_date_picker) {
    override val vm: MealViewModel by sharedViewModel()

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
                value = year
                maxValue = year + 1
                minValue = year - 1
            }
            dpMonthNp.run {
                descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                wrapSelectorWheel = false
                value = month
                maxValue = 12
                minValue = 1
            }
            dpDayNp.run {
                descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                wrapSelectorWheel = false
                value = day
                maxValue = 31
                minValue = 1
            }





            dpYearNp.minValue
            dpCancelTv.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun observeEvent() {

    }

}