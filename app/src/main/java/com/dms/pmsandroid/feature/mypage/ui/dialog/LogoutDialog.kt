package com.dms.pmsandroid.feature.mypage.ui.dialog

import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.databinding.DialogLogoutBinding
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import com.dms.pmsandroid.ui.MainViewModel
import org.koin.android.ext.android.inject

class LogoutDialog(override val vm: MyPageViewModel) :BaseDialog<DialogLogoutBinding>(R.layout.dialog_logout) {

    private val mainVm:MainViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkLogoutTv.setOnClickListener {
            vm.logout()
            dismiss()
            mainVm.needToLogin.value = Event(true)
        }
        binding.checkCancelTv.setOnClickListener {
            dismiss()
        }
    }
}