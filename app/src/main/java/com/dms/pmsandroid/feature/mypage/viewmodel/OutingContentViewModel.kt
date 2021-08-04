package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.mypage.model.OutingResponse

class OutingContentViewModel(private val myPageApiImpl: MyPageApiImpl):ViewModel() {

    private val _outing = MutableLiveData<List<OutingResponse>>()
    val outings : LiveData<List<OutingResponse>> get() = _outing

}