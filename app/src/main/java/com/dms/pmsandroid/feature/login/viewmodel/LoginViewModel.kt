package com.dms.pmsandroid.feature.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.LoginApiProvider

class LoginViewModel(
    private val apiProvider: LoginApiProvider,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {
    val doneLogin = MutableLiveData<Boolean>(false)
}