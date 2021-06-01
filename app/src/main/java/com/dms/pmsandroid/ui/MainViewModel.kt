package com.dms.pmsandroid.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.R
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.LoginApiProvider
import com.dms.pmsandroid.feature.login.model.LoginRequest

class MainViewModel(
    private val loginApiProvider: LoginApiProvider,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val tabSelectedItem = MutableLiveData<Int>(R.id.menu_calendar_it)

    val needToLogin = MutableLiveData<Boolean>()

    private val _doneToken = MutableLiveData<Boolean>(false)
    val doneToken: LiveData<Boolean> get() = _doneToken

    fun checkLogin() {
        val email = sharedPreferenceStorage.getInfo("user_email")
        val password = sharedPreferenceStorage.getInfo("user_password")

        if (email.isNotBlank() && password.isNotBlank()) {
            doLogin(email, password)
        } else {
            needToLogin.value = true
        }
    }

    private fun doLogin(email: String, password: String) {
        val request = LoginRequest(email, password)
        loginApiProvider.loginApi(request).subscribe { response ->
            when (response.code()) {
                200 -> {
                    sharedPreferenceStorage.saveInfo(response.body()!!.accessToken,"token")
                    _doneToken.value = true
                    Log.d("스케줄","토큰:${sharedPreferenceStorage.getInfo("access_token")}")
                }
                else -> {
                    needToLogin.value = true
                }
            }
        }
    }

}