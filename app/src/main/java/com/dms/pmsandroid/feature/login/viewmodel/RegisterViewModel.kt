package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dms.pmsandroid.data.remote.login.provideLoginApi

class RegisterViewModel {
    val registerApi = provideLoginApi()

    private val _userName = MutableLiveData<String>()
    private val _userEmail = MutableLiveData<String>()
    private val _userPassword = MutableLiveData<String>()
    val userName : LiveData<String> get() =_userName
    val userEmail : LiveData<String> get() = _userEmail
    val userPassword : LiveData<String> get() = _userPassword
}