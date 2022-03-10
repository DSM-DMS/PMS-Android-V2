package com.dms.pmsandroid.presentation.feature.mypage.ui.dialog

import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.presentation.base.BaseDialog
import com.dms.pmsandroid.presentation.base.Event
import com.dms.pmsandroid.databinding.DialogLogoutBinding
import com.dms.pmsandroid.presentation.feature.mypage.viewmodel.MyPageViewModel
import com.dms.pmsandroid.presentation.ui.MainViewModel
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