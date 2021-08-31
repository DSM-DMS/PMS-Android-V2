package com.dms.pmsandroid.feature.mypage.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.mypage.model.*

class MyPageViewModel(
    private val myPageApiImpl: MyPageApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val certification = MutableLiveData<String>()

    val successCertifitcation = MutableLiveData(false)

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> get() = _toastMessage

    private val _info = MutableLiveData<UserListResponse>()
    val info: LiveData<UserListResponse> get() = _info

    val newName = MutableLiveData<String>()

    val studentIndex = MutableLiveData(Event(0))

    fun changeName() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val nameRequest = ChangeNameRequest(newName.value!!.toString())
        myPageApiImpl.changeUserNameApi(accessToken, nameRequest).subscribe { nameResponse ->
            if (nameResponse.isSuccessful) {
                _toastMessage.value = Event("변경에 성공했습니다")
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
                    _toastMessage.value = Event("학생 등록에 성공하셨습니다")
                    successCertifitcation.value = true
                    inputBasicInfo()

                }
                400 -> {
                    _toastMessage.value = Event("입력하신 정보의 형식이 잘못되었습니다")

                }
                401 -> {
                    _toastMessage.value = Event("인증정보가 유효하지 않습니다")
                }
                404 -> {
                    _toastMessage.value = Event("해당 학생 정보가 없습니다")

                }
            }

        }
    }


    private val _basicInfo = MutableLiveData<BasicInformationResponse>()
    val basicInfo: LiveData<BasicInformationResponse> get() = _basicInfo

    fun loadStudentInfo() {
        val number = _info.value!!.students[0].studentNumber
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getUserApi(accessToken, number.toInt()).subscribe({
            if (it.isSuccessful) {
                _basicInfo.value = it.body()
            }
        }, {
        })
    }

    fun inputBasicInfo() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.getBasicInfo(accessToken).subscribe({
            if (it.isSuccessful) {
                _info.value = it.body()
                if (it.body()!!.students.isNotEmpty()) {
                    successCertifitcation.value = true
                    loadStudentInfo()
                }
            }
        }, {
        })
    }

    fun logout() {
        sharedPreferenceStorage.clearAll()
        successCertifitcation.value = false
    }

    fun deleteStudent(number: Int) {
        val request = DeleteStudentRequest(number)
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        myPageApiImpl.deleteStudent(accessToken, request).subscribe { response ->
            if (response.isSuccessful) {
                inputBasicInfo()
            } else {
                _toastMessage.value = Event("학생삭제를 실패하였습니다")
            }
        }
    }

}