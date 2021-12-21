package com.dms.pmsandroid.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.ProvideLoginApi
import com.dms.pmsandroid.feature.login.model.LoginRequest

class MainViewModel(
    private val provideLoginApi: ProvideLoginApi,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val needToLogin = MutableLiveData<Event<Boolean>>()

    val doneToken = MutableLiveData(false)

    private val _connectedInternet = MutableLiveData<Boolean>()
    val connectedInternet: LiveData<Boolean> get() = _connectedInternet

    fun checkLogin(internetConnected: Boolean) {
        if (!doneToken.value!!) {
            val email = sharedPreferenceStorage.getInfo("user_email")
            val password = sharedPreferenceStorage.getInfo("user_password")

            if (email.isNotBlank() || password.isNotBlank()) {
                if (internetConnected) {
                    doLogin(email, password)
                } else {
                    _connectedInternet.value = internetConnected
                }

            } else {
                needToLogin.value = Event(true)
            }
        }
    }

    private fun doLogin(email: String, password: String) {
        val request = LoginRequest(email, password)
        provideLoginApi.loginApi(request).subscribe({ response ->
            if (response.isSuccessful) {
                sharedPreferenceStorage.saveInfo(response.body()!!.accessToken, "access_token")
            } else {
                needToLogin.value = Event(true)
            }
            doneToken.value = true
        }, {
            needToLogin.value = Event(true)
        })
    }

}