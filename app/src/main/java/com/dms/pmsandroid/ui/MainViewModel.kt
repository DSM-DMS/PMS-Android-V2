package com.dms.pmsandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import org.koin.java.KoinJavaComponent.inject

class MainViewModel(private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel(){

    val tabSelectedItem = MutableLiveData<Int>()

    val needToLogin = MutableLiveData<Boolean>()

    fun checkLogin(){

    }
}