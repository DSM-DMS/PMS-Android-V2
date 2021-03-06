package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.SingleLiveEvent
import com.dms.pmsandroid.data.remote.login.ProvideLoginApi
import com.dms.pmsandroid.feature.login.model.RegisterRequest

class
RegisterViewModel(private val apiProvide: ProvideLoginApi) : ViewModel() {

    val userName = MutableLiveData<String>()
    val nEmptyName = MutableLiveData(false)

    val userEmail = MutableLiveData<String>()
    val nEmptyEmail = MutableLiveData(false)

    val userPassword = MutableLiveData<String>()
    val nEmptyPassword = MutableLiveData(false)

    val userPasswordCheck = MutableLiveData<String>()
    val samePassword = MutableLiveData(false)

    val doneInput = MutableLiveData<Boolean>()

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _finishRegister = SingleLiveEvent<Boolean>()
    val finishRegister: LiveData<Boolean> get() = _finishRegister

    private val _inProgress = MutableLiveData(false)
    val inProgress: LiveData<Boolean> get() = _inProgress

    private val _doneRegister = SingleLiveEvent<Boolean>()
    val doneRegister: LiveData<Boolean> get() = _doneRegister

    fun doRegister() {
        if (doneInput.value == true) {
            _inProgress.value = true
            val request = RegisterRequest(userEmail.value!!, userName.value!!, userPassword.value!!)
            apiProvide.registerApi(request).subscribe({ subscribe ->
                when (subscribe.code()) {
                    201 -> {
                        _toastMessage.value = "회원가입에 성공하셨습니다"
                        _doneRegister.call()
                    }
                    400 -> {
                        _toastMessage.value = "입력하신 정보의 형식이 잘못되었습니다"
                    }
                    409 -> {
                        _toastMessage.value = "해당 정보로 회원가입된 계정이 있습니다"
                    }
                    else -> {
                        _toastMessage.value = "회원가입에 실패하였습니다"
                    }
                }
                _inProgress.value = false
            }, {
                _toastMessage.value = "회원가입에 실패하였습니다"
                _inProgress.value = false
            })

        } else {
            _toastMessage.value = "정보를 정확히 입력해주세요"
        }

    }

    fun finishRegister() {
        _finishRegister.call()
    }

    fun clear() {
        _toastMessage.value = null
        userName.value = null
        userEmail.value = null
        userPassword.value = null
        userPasswordCheck.value = null
        doneInput.value = false
    }


}