package com.dms.pmsandroid.feature.mypage.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogStudentPlusBinding
import com.dms.pmsandroid.feature.mypage.viewmodel.AddStudentViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyPageAddStudentDialog : BaseDialog<DialogStudentPlusBinding>(R.layout.dialog_student_plus){
    override val vm : AddStudentViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.addstudentConfirmTv.setOnClickListener {

            dismiss()
        }
        binding.addstudentCancleTv.setOnClickListener {
            dismiss()
        }
    }

    override fun observeEvent() {

    }


}