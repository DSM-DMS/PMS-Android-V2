package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.mypage.model.OutingListResponse
import com.dms.pmsandroid.feature.mypage.model.OutingResponse

class OutingContentViewModel(private val myPageApiImpl: MyPageApiImpl,private val sharedPreferenceStorage: SharedPreferenceStorage):ViewModel() {

    private val _outing = MutableLiveData<OutingListResponse>()
    val outings : MutableLiveData<OutingListResponse> get() = _outing

    fun loadOuting(number : Int){
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getOutingApi(accessToken,number).subscribe({
            if(it.isSuccessful) {
                _outing.value = it.body()
            }
        }, {
        })
    }
}