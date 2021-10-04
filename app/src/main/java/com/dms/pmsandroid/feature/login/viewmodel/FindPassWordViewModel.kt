package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.SingleLiveEvent
import com.dms.pmsandroid.data.remote.login.LoginApiImpl
import com.dms.pmsandroid.feature.mypage.model.ResetPasswordRequest

class FindPassWordViewModel(private val loginApiImpl: LoginApiImpl) : ViewModel() {
    val email = MutableLiveData<String>()

    val inProgress = MutableLiveData<Boolean>()

    val doneInput = MutableLiveData<Boolean>()

    val doneResetPassword = SingleLiveEvent<Boolean>()

    val toastMessage = MutableLiveData<String>()

    fun sendEmail() {
        if (doneInput.value == true) {
            sendResetPasswordEmail()
        } else {
            toastMessage.value = "이메일을 입력해주세요"
        }
    }

    private fun sendResetPasswordEmail() {
        inProgress.value = true
        val request = ResetPasswordRequest(email.value.toString())
        loginApiImpl.resetPassword(request).subscribe { response ->
            when (response.code()) {
                200 -> {
                    doneResetPassword.call()
                    toastMessage.value = "이메일을 전송했습니다"
                }
                401 -> {
                    toastMessage.value = "존재하지 않는 이메일 입니다"
                }
                else -> {
                    toastMessage.value = "오류가 발생했습니다"
                }

            }
            inProgress.value = false
        }
    }

}