package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.LoginApiProvider
import com.dms.pmsandroid.feature.login.model.LoginRequest

class LoginViewModel(
    private val apiProvider: LoginApiProvider,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val userEmail = MutableLiveData<String>()
    val emailDone = MutableLiveData<Boolean>()

    val userPassword = MutableLiveData<String>()
    val passwordDone = MutableLiveData<Boolean>()

    val doneInput = MutableLiveData<Boolean>(false)

    private val _doneLogin = MutableLiveData<Boolean>(false)
    val doneLogin : LiveData<Boolean> get() = _doneLogin

    fun login(){
        if(doneInput.value!!){
            val request = LoginRequest(userEmail.value!!, userPassword.value!!)
            apiProvider.loginApi(request).subscribe { response->
                when(response.code()){
                    201->{
                        sharedPreferenceStorage.saveInfo(userEmail.value!!,"user_email")
                        sharedPreferenceStorage.saveInfo(userPassword.value!!,"user_password")
                        _doneLogin.value = true
                    }
                    else->{

                    }
                }
            }
        }
    }
}