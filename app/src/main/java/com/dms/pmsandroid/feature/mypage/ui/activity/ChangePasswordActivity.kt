package com.dms.pmsandroid.feature.mypage.ui.activity

import android.os.Bundle
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityChangePasswordBinding
import com.dms.pmsandroid.feature.mypage.viewmodel.ChangePasswordViewModel
import com.jakewharton.rxbinding4.widget.textChanges
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>(R.layout.activity_change_password) {
    override val vm: ChangePasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    override fun observeEvent() {
        binding.changePwPrePasswordEt.textChanges().subscribe{

        }
        binding.changePwPasswordEt.textChanges().subscribe{

        }
        binding.changePwCheckPasswordEt.textChanges().subscribe{

        }
    }
}