package com.dms.pmsandroid.ui

import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogDatePickerBinding

abstract class DatePickerDialog : BaseDialog<DialogDatePickerBinding>(R.layout.dialog_date_picker) {
    abstract val year: Int
    abstract val month: Int
    abstract val day: Int
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}