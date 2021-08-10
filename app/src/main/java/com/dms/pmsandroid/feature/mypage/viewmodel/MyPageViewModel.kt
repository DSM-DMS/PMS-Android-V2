package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.introduce.model.ClubListModel
import com.dms.pmsandroid.feature.login.viewmodel.RegisterViewModel
import com.dms.pmsandroid.feature.mypage.model.StudentCertificationResponse
import com.dms.pmsandroid.feature.mypage.model.StudentResponse
import com.dms.pmsandroid.feature.mypage.model.UserListResponse

class MyPageViewModel(
    private val myPageApiImpl: MyPageApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String> get() = _toastMessage

    private val _info = MutableLiveData<UserListResponse>()
    val info: LiveData<UserListResponse> get() = _info

    fun inputBasicInfo() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getBasicInfo(accessToken).subscribe({
            if (it.isSuccessful) {
               _info.value =  it.body()
            }
        }, {
        })
    }
}