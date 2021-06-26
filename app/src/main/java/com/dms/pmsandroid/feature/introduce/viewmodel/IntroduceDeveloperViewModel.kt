package com.dms.pmsandroid.feature.introduce.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.feature.introduce.adapter.DeveloperAdapter
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import com.dms.pmsandroid.ui.MainActivity

class IntroduceDeveloperViewModel: ViewModel() {
    val backPressed = MutableLiveData<Boolean>(false)

    fun backIntent(){
        backPressed.value = true
    }


}