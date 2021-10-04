package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.SingleLiveEvent
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.ProvideLoginApi
import com.dms.pmsandroid.feature.login.model.LoginRequest

class LoginViewModel(
    private val apiProvide: ProvideLoginApi,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val userEmail = MutableLiveData<String>()
    val emailDone = MutableLiveData(false)

    val userPassword = MutableLiveData<String>()
    val passwordDone = MutableLiveData(false)

    val needRegister = SingleLiveEvent<Unit>()

    val findPassword = SingleLiveEvent<Unit>()

    val doneInput = MutableLiveData(false)

    private val _doneLogin = MutableLiveData(false)
    val doneLogin: LiveData<Boolean> get() = _doneLogin

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _inProgress = MutableLiveData(false)
    val inProgress: LiveData<Boolean> get() = _inProgress

    val autoLogin = MutableLiveData(false)

    fun login() {
        if (doneInput.value!!) {
            _inProgress.value = true
            val request = LoginRequest(userEmail.value!!, userPassword.value!!)
            apiProvide.loginApi(request).subscribe({
                when (it.code()) {
                    200 -> {
                        if (autoLogin.value!!) {
                            sharedPreferenceStorage.saveInfo(userEmail.value!!, "user_email")
                        }
                        sharedPreferenceStorage.saveInfo(userPassword.value!!, "user_password")
                        sharedPreferenceStorage.saveInfo(it.body()!!.accessToken, "access_token")
                        _doneLogin.value = true
                    }
                    else -> {
                        _toastMessage.value = "잘못된 로그인 정보입니다"
                    }
                }
                _inProgress.value = false
            }, {
                _toastMessage.value = "로그인에 실패하였습니다"
                _inProgress.value = false
            })
        } else {
            _toastMessage.value = "정보를 모두 입력해주세요"
        }
    }

    fun findPassword() {
        clear()
        findPassword.call()
    }

    fun needRegister() {
        clear()
        needRegister.call()
    }

    private fun clear() {
        userEmail.value = null
        userPassword.value = null
        _toastMessage.value = null
    }
}