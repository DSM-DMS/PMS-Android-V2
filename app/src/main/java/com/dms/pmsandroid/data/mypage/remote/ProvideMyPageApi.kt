package com.dms.pmsandroid.data.mypage.remote

import com.dms.pmsandroid.data.remote.SmoothBearApi
import com.dms.pmsandroid.presentation.feature.mypage.model.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class ProvideMyPageApi(api: SmoothBearApi) {
    private val myPageApi = api.retrofit.create(MyPageApi::class.java)

    fun getBasicInfo(accessToken: String): @NonNull Single<Response<UserListResponse>> =
        myPageApi.getStudents(accessToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getUserApi(
        accessToken: String,
        number: Int
    ): @NonNull Single<Response<BasicInformationResponse>> =
        myPageApi.getStudentInformation(accessToken, number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun getPointApi(
        accessToken: String,
        number: Int
    ): @NonNull Single<Response<PointListResponse>> =
        myPageApi.getStudentPoint(accessToken, number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun getOutingApi(
        accessToken: String,
        number: Int
    ): @NonNull Single<Response<OutingListResponse>> =
        myPageApi.getStudentOuting(accessToken, number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun certificationStudentApi(
        accessToken: String,
        request: StudentCertificationResponse
    ): @NonNull Single<Response<Void>> =
        myPageApi.studentCertification(accessToken, request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun changeUserNameApi(
        token: String,
        changeNameRequest: ChangeNameRequest
    ): @NonNull Single<Response<Unit>> =
        myPageApi.changeUserName(token, changeNameRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun deleteStudent(
        token: String,
        request: DeleteStudentRequest
    ): @NonNull Single<Response<Unit>> =
        myPageApi.deleteStudent(token, request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}