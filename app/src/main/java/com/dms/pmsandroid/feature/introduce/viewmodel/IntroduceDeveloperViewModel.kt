package com.dms.pmsandroid.feature.introduce.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import java.util.ArrayList

class IntroduceDeveloperViewModel : ViewModel() {

    var liveData: MutableLiveData<ArrayList<DevelopModel>> = MutableLiveData<ArrayList<DevelopModel>>()


    init {
        var DevelopData = ArrayList<DevelopModel>()
        DevelopData.add(DevelopModel("김재원", "Android", "jaewon"))
        DevelopData.add(DevelopModel("이은별", "Android", "eunbyul"))
        DevelopData.add(DevelopModel("정고은", "IOS", "goeun"))
        DevelopData.add(DevelopModel("강은빈", "FrontEnd", "eunbeen"))
        DevelopData.add(DevelopModel("이진우", "FrontEnd", "jinwoo"))
        DevelopData.add(DevelopModel("김정빈", "Server", "jungbin2"))
        DevelopData.add(DevelopModel("정지우", "Server", "jiwoo"))
        liveData.postValue(DevelopData)
    }
}