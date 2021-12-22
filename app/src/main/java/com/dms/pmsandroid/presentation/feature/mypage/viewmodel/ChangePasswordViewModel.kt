package com.dms.pmsandroid.presentation.feature.mypage.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.ProvideLoginApi
import com.dms.pmsandroid.presentation.feature.mypage.model.ChangePasswordRequest

class ChangePasswordViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val provideLoginApi: ProvideLoginApi
) : ViewModel() {
    val prePassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val newCheckedPassword = MutableLiveData<String>()

    val doneInput = MutableLiveData(false)

    private val _inProgress = MutableLiveData(false)
    val inProgress: LiveData<Boolean> get() = _inProgress

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> get() = _toast

    val resetPassword = MutableLiveData<Boolean>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkResetPasswordTime() {
        val savedTimeString = sharedPreferenceStorage.getInfo("reset_password_time")
        if (savedTimeString != "") {
            val savedTime = savedTimeString.toLong()
            val currentTime = System.currentTimeMillis()
            val minuteDiff = (currentTime - savedTime)/60000
            if(minuteDiff<6){
                resetPassword.value = true
                prePassword.value = sharedPreferenceStorage.getInfo("user_password")
            }
        }

    }

    fun changePassword() {
        if (doneInput.value!!) {
            _inProgress.value = true
            val token = sharedPreferenceStorage.getInfo("access_token")
            val changePasswordRequest =
                ChangePasswordRequest(newPassword.value!!, prePassword.value!!)
            provideLoginApi.changePassword(token, changePasswordRequest).subscribe { response ->
                _inProgress.value = false
                when (response.code()) {
                    201 -> {
                        sharedPreferenceStorage.saveInfo(newPassword.value!!, "user_password")
                        _toast.value = "완료되었습니다"
                    }
                    401 -> {
                        _toast.value = "잘못된 현재 비밀번호입니다"
                    }
                    403 -> {
                        _toast.value = "이미 사용중인 비밀번호입니다"
                    }
                    else -> {
                        _toast.value = "비밀번호 변경에 실패하였습니다"
                    }
                }

            }
        }
    }


}
