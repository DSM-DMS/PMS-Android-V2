package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.LoginApiImpl
import com.dms.pmsandroid.feature.login.model.LoginRequest

class LoginViewModel(
        private val apiImpl: LoginApiImpl,
        private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val userEmail = MutableLiveData<String>()
    val emailDone = MutableLiveData<Boolean>(false)

    val userPassword = MutableLiveData<String>()
    val passwordDone = MutableLiveData<Boolean>(false)
    // SingleLiveEvent & Event Wrapper

    val needRegister = MutableLiveData<Boolean>(false)

    val doneInput = MutableLiveData<Boolean>(false)

    private val _doneLogin = MutableLiveData<Boolean>(false)
    val doneLogin : LiveData<Boolean> get() = _doneLogin

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String> get() = _toastMessage

    fun login(){
        if(doneInput.value!!){
            val request = LoginRequest(userEmail.value!!, userPassword.value!!)
            apiImpl.loginApi(request).subscribe({
                when(it.code()){
                    200->{
                        sharedPreferenceStorage.saveInfo(userEmail.value!!,"user_email")
                        sharedPreferenceStorage.saveInfo(userPassword.value!!,"user_password")
                        sharedPreferenceStorage.saveInfo(it.body()!!.accessToken,"access_token")
                        _doneLogin.value = true
                    }
                    else->{
                        _toastMessage.value = "잘못된 로그인 정보입니다"
                    }
                }
            },{
                _toastMessage.value = "로그인에 실패하였습니다"
            })
        }
        else{
            _toastMessage.value = "정보를 모두 입력해주세요"
        }
    }

    fun needRegister(){
        userEmail.value = null
        userPassword.value = null
        needRegister.value = true
    }
}