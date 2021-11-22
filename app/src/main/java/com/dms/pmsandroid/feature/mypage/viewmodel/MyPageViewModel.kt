package com.dms.pmsandroid.feature.mypage.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.base.Event
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.login.ProvideLoginApi
import com.dms.pmsandroid.data.remote.mypage.ProvideMyPageApi
import com.dms.pmsandroid.data.remote.notification.ProvideNotificationApi
import com.dms.pmsandroid.feature.login.model.LoginRequest
import com.dms.pmsandroid.feature.mypage.model.*

class MyPageViewModel(
    private val provideMyPageApi: ProvideMyPageApi,
    private val notificationApi: ProvideNotificationApi,
    private val provideLoginApi: ProvideLoginApi,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val certification = MutableLiveData<String>()

    val successCertification = MutableLiveData(false)

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> get() = _toastMessage

    private val _info = MutableLiveData<UserListResponse>()
    val info: LiveData<UserListResponse> get() = _info

    private val _students = MutableLiveData<List<StudentResponse>>()
    val students: LiveData<List<StudentResponse>> get() = _students

    val newName = MutableLiveData<String>()

    val studentIndex = MutableLiveData(Event(0))

    private val _studentInfo = MutableLiveData<BasicInformationResponse>()
    val studentInfo: LiveData<BasicInformationResponse> get() = _studentInfo

    init {
        studentIndex.value = Event(sharedPreferenceStorage.getIntInfo("student_index"))
    }

    fun changeName() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val nameRequest = ChangeNameRequest(newName.value!!.toString())
        provideMyPageApi.changeUserNameApi(accessToken, nameRequest).subscribe { nameResponse ->
            if (nameResponse.isSuccessful) {
                _toastMessage.value = Event("변경에 성공했습니다")
                loadBaseInfo()
            }
        }
    }

    fun studentCertification() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val request = StudentCertificationResponse(certification.value!!)
        provideMyPageApi.certificationStudentApi(accessToken, request).subscribe { response ->
            when (response.code()) {
                201 -> {
                    _toastMessage.value = Event("학생 등록에 성공하셨습니다")
                    loadBaseInfo()
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
            certification.value = null
        }
    }


    fun loadStudentInfo(index: Int) {
        if (students.value != null) {
            val number =
                students.value!![index].studentNumber
            val accessToken = sharedPreferenceStorage.getInfo("access_token")
            sharedPreferenceStorage.saveInfo(number, "student_number")
            provideMyPageApi.getUserApi(accessToken, number).subscribe { it ->
                if (it.isSuccessful) {
                    _studentInfo.value = it.body()
                }
            }
        }

    }

    fun loadBaseInfo() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        provideMyPageApi.getBasicInfo(accessToken).subscribe{ it ->
            if (it.isSuccessful) {
                _info.value = it.body()
                if (it.body() != null) {
                    _students.value =
                        (it.body()!!.students as MutableList<StudentResponse>).sortedWith(
                            compareBy { student -> student.studentNumber })
                }
                if (it.body()!!.students.isNotEmpty()) {
                    successCertification.value = true
                    loadStudentInfo(studentIndex.value!!.peekContent())
                } else {
                    successCertification.value = false
                }
            } else if(it.code() == 401) {
                provideLoginApi.loginApi(LoginRequest(sharedPreferenceStorage.getInfo("user_email"), sharedPreferenceStorage.getInfo("user_password")))
                    .subscribe { it ->
                        if(it.isSuccessful) {
                            sharedPreferenceStorage.saveInfo(it.body()!!.accessToken, "access_token")
                            loadBaseInfo()
                        }
                    }
            }
        }
    }

    fun logout() {
        sharedPreferenceStorage.clearAll()
        successCertification.value = false
        notificationApi.unSubscribeNotification()
    }

    fun deleteStudent(number: Int) {
        val request = DeleteStudentRequest(number)
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        provideMyPageApi.deleteStudent(accessToken, request).subscribe { response ->
            if (response.isSuccessful) {
                studentIndex.value = Event(0)
                loadBaseInfo()
            } else {
                _toastMessage.value = Event("학생삭제를 실패하였습니다")
            }
        }
    }

    fun saveIndex(index: Int) {
        sharedPreferenceStorage.saveInfo(index, "student_index")
    }

}