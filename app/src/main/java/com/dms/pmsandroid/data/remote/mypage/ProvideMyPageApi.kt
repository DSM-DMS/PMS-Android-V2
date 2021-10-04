package com.dms.pmsandroid.data.remote.mypage

import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.feature.mypage.model.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class ProvideMyPageApi {
    private fun providerMyPageApi(): MyPageApi =
        ApiProvider.jungBinRetroFitBuilder.create(MyPageApi::class.java)

    fun getBasicInfo(accessToken: String): @NonNull Single<Response<UserListResponse>> =
        providerMyPageApi().getStudents(accessToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getUserApi(accessToken: String,number: Int): @NonNull Single<Response<BasicInformationResponse>> =
        providerMyPageApi().getStudentInformation(accessToken,number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun getPointApi(accessToken: String,number: Int): @NonNull Single<Response<PointListResponse>> =
        providerMyPageApi().getStudentPoint(accessToken,number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun getOutingApi(accessToken: String,number: Int): @NonNull Single<Response<OutingListResponse>> =
        providerMyPageApi().getStudentOuting(accessToken,number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun certificationStudentApi(accesstoken:String,request :StudentCertificationResponse):@NonNull Single<Response<Void>> = providerMyPageApi().StudentCertification(accesstoken,request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun changeUserNameApi(token:String,changeNameRequest: ChangeNameRequest):@NonNull Single<Response<Unit>> = providerMyPageApi().changeUserName(token,changeNameRequest)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun deleteStudent(token: String,request:DeleteStudentRequest):@NonNull Single<Response<Unit>> = providerMyPageApi().deleteStudent(token,request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}