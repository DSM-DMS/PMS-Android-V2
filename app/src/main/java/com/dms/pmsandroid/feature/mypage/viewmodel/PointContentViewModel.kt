package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.mypage.ProvideMyPageApi
import com.dms.pmsandroid.feature.mypage.model.PointListResponse

class PointContentViewModel(private val provideMyPageApi: ProvideMyPageApi, private val sharedPreferenceStorage: SharedPreferenceStorage):ViewModel(){

   private val _point = MutableLiveData<PointListResponse>()
   val points : LiveData<PointListResponse> get() = _point

   fun loadPoint(number: Int) {
      val accessToken = sharedPreferenceStorage.getInfo("access_token")
      provideMyPageApi.getPointApi(accessToken, number).subscribe({
         if (it.isSuccessful) {
            _point.value = it.body()
         }
      }, {
      })
   }
}
