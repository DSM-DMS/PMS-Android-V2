package com.dms.pmsandroid.feature.introduce.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainIntroViewModel : ViewModel() {

    val devintroduceClick = MutableLiveData<Boolean>(false)
    val clubIntroduceClick = MutableLiveData<Boolean>(false)
    val workIntroduceClick = MutableLiveData<Boolean>(false)


    fun devClicked(){
        devintroduceClick.value = true
    }

    fun clubClicked(){
        clubIntroduceClick.value = true
    }

    fun workClicked(){
        workIntroduceClick.value = true
    }

}