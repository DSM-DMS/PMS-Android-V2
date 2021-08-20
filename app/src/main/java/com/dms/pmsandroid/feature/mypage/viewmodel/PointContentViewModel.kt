package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.mypage.model.PointResponse

class PointContentViewModel(private val myPageApiImpl: MyPageApiImpl,private val sharedPreferenceStorage: SharedPreferenceStorage):ViewModel(){

   private val _point = MutableLiveData<List<PointResponse>>()
   val points : LiveData<List<PointResponse>> get() = _point







}
