package com.dms.pmsandroid.feature.mypage.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.introduce.model.ClubListModel
import com.dms.pmsandroid.feature.introduce.ui.activity.IntroduceClubDetailActivity
import com.dms.pmsandroid.feature.login.viewmodel.RegisterViewModel
import com.dms.pmsandroid.feature.mypage.model.*
import java.util.ArrayList

class MyPageViewModel(
    private val myPageApiImpl: MyPageApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val certification = MutableLiveData<String>()

    private val _successCertifitcation = MutableLiveData(false)
    val successCertifitcation: LiveData<Boolean> get() = _successCertifitcation


    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _info = MutableLiveData<UserListResponse>()
    val info: LiveData<UserListResponse> get() = _info

    val newName = MutableLiveData<String>()
    val introComment = MutableLiveData<String>()

    fun changeName() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val nameRequest = ChangeNameRequest(newName.value!!.toString())
        myPageApiImpl.changeUserNameApi(accessToken, nameRequest).subscribe { nameResponse ->
            if (nameResponse.isSuccessful) {
                _toastMessage.value = "변경에 성공했습니다"
                inputBasicInfo()
            }

        }
    }


    fun studentCertification() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val request = StudentCertificationResponse(certification.value!!)
        myPageApiImpl.certificationStudentApi(accessToken, request).subscribe { request ->
            when (request.code()) {
                201 -> {
                    _toastMessage.value = "학생 등록에 성공하셨습니다"
                    _successCertifitcation.value = true
                }
                400 -> {
                    _toastMessage.value = "입력하신 정보의 형식이 잘못되었습니다"

                }
                401 -> {
                    _toastMessage.value = "인증정보가 유효하지 않습니다"
                }
                404 -> {
                    _toastMessage.value = "해당 학생 정보가 없습니다"

                }
            }

        }
    }


    private val _BasicInfo = MutableLiveData<BasicInformationResponse>()
    val BasicInfo: LiveData<BasicInformationResponse> get() = _BasicInfo

    fun getBasicInfo(number: Int) {
        if (successCertifitcation.value == true) {
            myPageApiImpl.getUserApi(number).subscribe({
                if (it.isSuccessful) {
                    _BasicInfo.value = it.body()
                }
            }, {
            })
        }
    }

    fun inputBasicInfo() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getBasicInfo(accessToken).subscribe({
            if (it.isSuccessful) {
                _info.value = it.body()
            }
        }, {
        })
    }

}