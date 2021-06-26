package com.dms.pmsandroid.feature.introduce.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntroduceClubViewModel : ViewModel() {
    val backClick = MutableLiveData<Boolean>(false)

    fun backClicked(){
        backClick.value = true
    }



}