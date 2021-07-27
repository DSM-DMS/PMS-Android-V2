package com.dms.pmsandroid.data.remote.mypage

import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.feature.mypage.model.BasicInformationResponse
import com.dms.pmsandroid.feature.mypage.model.OutingListResponse
import com.dms.pmsandroid.feature.mypage.model.PointListResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MyPageApiImpl {
    private fun provideMyPageApi(): MyPageApi =
        ApiProvider.jungBinRetroFitBuilder.create(MyPageApi::class.java)


    fun getUser(number: Int): @NonNull Single<Response<BasicInformationResponse>> =
        provideMyPageApi().getStudentInformation(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun getPoint(number: Int): @NonNull Single<Response<PointListResponse>> =
        provideMyPageApi().getStudentPoint(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun getOuting(number: Int): @NonNull Single<Response<OutingListResponse>> =
        provideMyPageApi().getStudentOuting(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


}