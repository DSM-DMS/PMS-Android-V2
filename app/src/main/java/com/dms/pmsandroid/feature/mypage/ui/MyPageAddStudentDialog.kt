package com.dms.pmsandroid.feature.mypage.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogStudentPlusBinding
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageAddStudentDialog(override val vm: MyPageViewModel) : BaseDialog<DialogStudentPlusBinding>(R.layout.dialog_student_plus){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.addstudentConfirmTv.setOnClickListener {
            if(binding.checkcodeEt.text.length==6){
                dismiss()
                vm.studentCertification()
            }
        }

        binding.addstudentCancleTv.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
            dismiss()
        }
    }

    override fun observeEvent() {
    }


}