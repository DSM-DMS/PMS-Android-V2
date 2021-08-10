package com.dms.pmsandroid.base

import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.DialogDatePickerBinding

abstract class DatePickerDialog : BaseDialog<DialogDatePickerBinding>(R.layout.dialog_date_picker) {
    abstract val year: Int
    abstract val month: Int
    abstract val day: Int
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                onCompleteClicked()
            }
            dpCancelTv.setOnClickListener {
                dismiss()
            }
        }
    }

    abstract fun onCompleteClicked()
}