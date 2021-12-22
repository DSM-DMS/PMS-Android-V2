package com.dms.pmsandroid.presentation.feature.mypage.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.presentation.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogChangeNameBinding
import com.dms.pmsandroid.presentation.feature.mypage.viewmodel.MyPageViewModel

class ChangeNameDialog(override val vm: MyPageViewModel) :
    BaseDialog<DialogChangeNameBinding>(R.layout.dialog_change_name) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        vm.newName.value = vm.info.value?.name
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