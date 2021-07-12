package com.dms.pmsandroid.feature.introduce.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainIntroduceViewModel() : ViewModel() {

    val devintroduceClick = MutableLiveData<Boolean>(false)
    val clubIntroduceClick = MutableLiveData<Boolean>(false)
    val workIntroduceClick = MutableLiveData<Boolean>(false)

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