package com.dms.pmsandroid.feature.login.viewmodel

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

    val doneLogin = MutableLiveData<Boolean>(false)



    fun login(){
        if(doneLogin.value!!){
            val request = LoginRequest(userEmail.value!!, userPassword.value!!)
            apiProvider.loginApi(request).subscribe { response->
                when(response.code()){
                    201->{

                    }
                    else->{

                    }
                }
            }
        }
    }
}