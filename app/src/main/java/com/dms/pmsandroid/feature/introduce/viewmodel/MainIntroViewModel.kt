package com.dms.pmsandroid.feature.introduce.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.feature.introduce.ui.activity.IntroduceDeveloperActivity

class MainIntroViewModel : ViewModel() {

    val introduceClick = MutableLiveData<Boolean>(false)


    fun devClicked(){
        introduceClick.value = true
    }

}