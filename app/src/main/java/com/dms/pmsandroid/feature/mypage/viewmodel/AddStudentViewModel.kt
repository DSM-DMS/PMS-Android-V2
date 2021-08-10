package com.dms.pmsandroid.feature.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.data.remote.mypage.MyPageApiImpl
import com.dms.pmsandroid.feature.mypage.model.StudentCertificationResponse

class AddStudentViewModel(private val myPageApiImpl: MyPageApiImpl):ViewModel(){

    private val _toastMessage = MutableLiveData<String>()

    val doneInput = MutableLiveData(false)
    val certification = MutableLiveData<String>()

    private val _successCertifitcation = MutableLiveData(false)
    val successCertifitcation : LiveData<Boolean> get() = _successCertifitcation


    fun studentCertification(){
        if(doneInput.value!!){
            val request = StudentCertificationResponse(certification.value!!)
            myPageApiImpl.certificationStudentApi(request).subscribe{request->
                when(request.code()){
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
    }
}