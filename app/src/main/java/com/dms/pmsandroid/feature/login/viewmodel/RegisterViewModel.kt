package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.remote.login.LoginApiProvider
import com.dms.pmsandroid.feature.login.model.RegisterRequest

class RegisterViewModel(private val apiProvider: LoginApiProvider) : ViewModel() {

    val userName = MutableLiveData<String>()
    val nEmptyName = MutableLiveData<Boolean>(false)

    val userEmail = MutableLiveData<String>()
    val nEmptyEmail = MutableLiveData<Boolean>(false)

    val userPassword = MutableLiveData<String>()
    val nEmptyPassword = MutableLiveData<Boolean>(false)

    val userPasswordCheck = MutableLiveData<String>()
    val samePassword = MutableLiveData<Boolean>(false)

    val doneInput = MutableLiveData<Boolean>()

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _finishRegister = MutableLiveData<Boolean>(false)
    val finishRegister: LiveData<Boolean> get() = _finishRegister

    fun doRegister() {
        if (doneInput.value == true) {
            val request = RegisterRequest(userEmail.value!!, userName.value!!, userPassword.value!!)
            apiProvider.registerApi(request).subscribe { subscribe ->
                when (subscribe.code()) {
                    201 -> {
                        _toastMessage.value = "회원가입에 성공하셨습니다"
                        _finishRegister.value = true
                    }
                    400 -> {
                        _toastMessage.value = "입력하신 정보가 잘못되었습니다"
                    }
                    409 -> {
                        _toastMessage.value = "해당 정보로 회원가입된 계정이 있습니다"
                    }
                }
            }
        } else {
            _toastMessage.value = "정보를 정확히 입력해주세요"
        }

    }

    fun finishRegister() {
        _finishRegister.value = true
    }


}