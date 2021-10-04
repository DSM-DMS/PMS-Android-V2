package com.dms.pmsandroid.feature.login.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.SingleLiveEvent
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.LoginApiImpl
import com.dms.pmsandroid.feature.mypage.model.ResetPasswordRequest
import java.time.LocalDateTime

class FindPassWordViewModel(
    private val loginApiImpl: LoginApiImpl,
    private val localStorage: SharedPreferenceStorage
) : ViewModel() {
    val email = MutableLiveData<String>()

    val inProgress = MutableLiveData<Boolean>()

    val doneInput = MutableLiveData<Boolean>()

    val doneResetPassword = SingleLiveEvent<Boolean>()

    val toastMessage = MutableLiveData<String>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendEmail() {
        if (doneInput.value == true) {
            sendResetPasswordEmail()
        } else {
            toastMessage.value = "이메일을 입력해주세요"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendResetPasswordEmail() {
        inProgress.value = true
        val request = ResetPasswordRequest(email.value.toString())
        loginApiImpl.resetPassword(request).subscribe { response ->
            when (response.code()) {
                200 -> {
                    doneResetPassword.call()
                    val currentTime = System.currentTimeMillis()
                    localStorage.saveInfo(currentTime.toString(), "reset_password_time")
                    toastMessage.value = "이메일을 전송했습니다"
                }
                401, 500 -> {
                    toastMessage.value = "존재하지 않는 이메일 입니다"
                }
                else -> {
                    toastMessage.value = "오류가 발생했습니다"
                }

            }
            inProgress.value = false
        }
    }

    fun clear() {
        toastMessage.value = null
        doneInput.value = false
    }

}