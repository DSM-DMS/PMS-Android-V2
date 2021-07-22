package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddStudentViewModel():ViewModel() {
    val checkConfirm = MutableLiveData<Boolean>(false)
    val checkCancel =  MutableLiveData<Boolean>(false)





}