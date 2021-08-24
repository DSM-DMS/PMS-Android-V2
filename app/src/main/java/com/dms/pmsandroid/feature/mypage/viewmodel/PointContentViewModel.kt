package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.mypage.model.PointListResponse
import com.dms.pmsandroid.feature.mypage.model.PointResponse

class PointContentViewModel(private val myPageApiImpl: MyPageApiImpl,private val sharedPreferenceStorage: SharedPreferenceStorage):ViewModel(){

   private val _point = MutableLiveData<PointListResponse>()
   val points : LiveData<PointListResponse> get() = _point

   fun loadPoint(number: Int) {
      val accessToken = sharedPreferenceStorage.getInfo("access_token")
      myPageApiImpl.getPointApi(accessToken, number).subscribe({
         if (it.isSuccessful) {
            _point.value = it.body()
         }
      }, {
      })
   }
}
