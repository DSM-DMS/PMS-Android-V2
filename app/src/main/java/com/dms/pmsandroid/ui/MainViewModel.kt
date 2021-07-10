package com.dms.pmsandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.R
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.LoginApiImpl
import com.dms.pmsandroid.feature.login.model.LoginRequest

class MainViewModel(
        private val loginApiImpl: LoginApiImpl,
        private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val tabSelectedItem = MutableLiveData(R.id.menu_calendar_it)

    val needToLogin = MutableLiveData<Boolean>()

    val doneToken = MutableLiveData(false)

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
        loginApiImpl.loginApi(request).subscribe { response ->
            when (response.code()) {
                200 -> {
                    sharedPreferenceStorage.saveInfo(response.body()!!.accessToken,"access_token")
                }
                else -> {
                    needToLogin.value = true
                }
            }
            doneToken.value = true
        }
    }

}