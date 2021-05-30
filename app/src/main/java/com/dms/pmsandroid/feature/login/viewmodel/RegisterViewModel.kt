package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.remote.login.LoginApiProvider
import com.dms.pmsandroid.feature.login.model.RegisterRequest

class RegisterViewModel(private val apiProvider:LoginApiProvider) : ViewModel(){

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
    val toastMessage:LiveData<String> get() = _toastMessage

    fun doRegister(){
        if(doneInput.value == true){
            val request = RegisterRequest(userEmail.value!!,userName.value!!, userPassword.value!!)
            apiProvider.registerApi(request).subscribe { subscribe ->
                when(subscribe.code()){
                    201 -> {

                    }
                    400 -> {

                    }
                    409 -> {

                    }
                }
            }
        }else{
            _toastMessage.value = "정보를 정확히 입력해주세요"
        }

    }


}