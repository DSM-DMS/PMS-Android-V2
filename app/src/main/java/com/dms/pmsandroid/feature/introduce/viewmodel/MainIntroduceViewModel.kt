package com.dms.pmsandroid.feature.introduce.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainIntroduceViewModel : ViewModel() {

    val devintroduceClick = MutableLiveData(false)
    val clubIntroduceClick = MutableLiveData(false)
    val workIntroduceClick = MutableLiveData(false)

    fun clubIntroClicked(){
        clubIntroduceClick.value = true
    }

    fun devIntroClicked(){
        devintroduceClick.value = true
    }

    fun workIntroClicked(){
        workIntroduceClick.value = true
    }


}