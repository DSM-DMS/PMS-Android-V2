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
        binding.run {
            dpYearNp.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            dpMonthNp.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            dpDayNp.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            dpCancelTv.setOnClickListener {
                dismiss()
            }
        }
    }
    override fun observeEvent() {

    }

}