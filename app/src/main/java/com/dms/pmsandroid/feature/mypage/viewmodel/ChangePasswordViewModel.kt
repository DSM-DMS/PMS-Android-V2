package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.LoginApiImpl

class ChangePasswordViewModel(private val sharedPreferenceStorage: SharedPreferenceStorage,private val loginApiImpl: LoginApiImpl) : ViewModel() {

}