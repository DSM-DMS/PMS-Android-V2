package com.dms.pmsandroid.presentation.feature.mypage.ui.activity

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.dms.pmsandroid.R
import com.dms.pmsandroid.presentation.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityChangePasswordBinding
import com.dms.pmsandroid.presentation.feature.mypage.viewmodel.ChangePasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity :
    BaseActivity<ActivityChangePasswordBinding>(R.layout.activity_change_password) {
    override val vm: ChangePasswordViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.checkResetPasswordTime()
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.changePwBtn.setOnClickListener {
            vm.changePassword()
        }
    }

    var newPasswordDone = false
    var newPasswordCheckDone = false
    override fun observeEvent() {
        vm.run {
            toast.observe(this@ChangePasswordActivity,{
                Toast.makeText(this@ChangePasswordActivity, it, Toast.LENGTH_SHORT).show()
                if (it == "완료되었습니다"){
                    finish()
                }
            })
            prePassword.observe(this@ChangePasswordActivity, {
                checkDoneInput()
            })
            newPassword.observe(this@ChangePasswordActivity, {
                if (it.length in 8..20) {
                    binding.changePwPasswordTl.error = null
                    newPasswordDone = true
                } else {
                    newPasswordDone = false
                    binding.changePwPasswordTl.error = "8~20자리 사이의 비밀번호를 입력해주세요"
                }
                checkDoneInput()
            })
            newCheckedPassword.observe(this@ChangePasswordActivity, {
                if(it == newPassword.value){
                    binding.changePwCheckPasswordTl.error = null
                    newPasswordCheckDone = true
                }else{
                    binding.changePwCheckPasswordTl.error = "비밀번호가 다릅니다"
                    newPasswordCheckDone = false
                }
                checkDoneInput()
            })
        }
    }

    private fun checkDoneInput() {
        vm.doneInput.value =
            !vm.prePassword.value.isNullOrBlank() && newPasswordDone && newPasswordCheckDone
    }
}