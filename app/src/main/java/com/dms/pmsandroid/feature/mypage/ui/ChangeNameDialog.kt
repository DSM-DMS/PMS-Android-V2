package com.dms.pmsandroid.feature.mypage.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogChangeNameBinding
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeNameDialog : BaseDialog<DialogChangeNameBinding>(R.layout.dialog_change_name) {
    override val vm: MyPageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.confirmTv.setOnClickListener {
            vm.changeName()
            dismiss()

        }
        binding.cancleTv.setOnClickListener {
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