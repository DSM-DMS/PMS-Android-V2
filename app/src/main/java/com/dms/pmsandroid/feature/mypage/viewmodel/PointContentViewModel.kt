package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.mypage.model.PointModel

class PointContentViewModel(private val myPageApiImpl: MyPageApiImpl):ViewModel(){

   private val _point = MutableLiveData<List<PointModel>>()
   val points : LiveData<List<PointModel>> get() = _point







}
