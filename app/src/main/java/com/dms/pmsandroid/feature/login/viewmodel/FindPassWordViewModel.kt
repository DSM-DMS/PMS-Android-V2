package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FindPassWordViewModel: ViewModel() {
    val email = MutableLiveData<String>()

    val inProgress = MutableLiveData<Boolean>()

    fun sendEmail(){

    }
    //유효한 이메일 아니면 401
    //인증시간 30분

}