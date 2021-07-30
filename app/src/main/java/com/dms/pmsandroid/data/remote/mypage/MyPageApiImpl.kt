package com.dms.pmsandroid.data.remote.mypage

import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.feature.mypage.model.BasicInformationResponse
import com.dms.pmsandroid.feature.mypage.model.OutingListResponse
import com.dms.pmsandroid.feature.mypage.model.PointListResponse
import com.dms.pmsandroid.feature.mypage.model.StudentCertificationResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MyPageApiImpl {
    private fun providerMyPageApi(): MyPageApi =
        ApiProvider.jungBinRetroFitBuilder.create(MyPageApi::class.java)


    fun getUserApi(number: Int): @NonNull Single<Response<BasicInformationResponse>> =
        providerMyPageApi().getStudentInformation(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun getPointApi(number: Int): @NonNull Single<Response<PointListResponse>> =
        providerMyPageApi().getStudentPoint(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun getOutingApi(number: Int): @NonNull Single<Response<OutingListResponse>> =
        providerMyPageApi().getStudentOuting(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun certificationStudentApi(request :StudentCertificationResponse):@NonNull Single<Response<Void>> = providerMyPageApi().StudentCertification(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())


}