package com.dms.pmsandroid.feature.mypage.ui.activity

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityChangePasswordBinding
import com.dms.pmsandroid.feature.mypage.viewmodel.ChangePasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>(R.layout.activity_change_password) {
    override val vm: ChangePasswordViewModel by viewModel()

    override fun observeEvent() {

    }
}