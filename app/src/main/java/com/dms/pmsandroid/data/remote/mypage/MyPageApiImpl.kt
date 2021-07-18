package com.dms.pmsandroid.data.remote.mypage

import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.feature.mypage.model.PointListResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MyPageApiImpl {
    private fun provideMyPageApi(): MyPageApi = ApiProvider.jungBinRetroFitBuilder.create(MyPageApi::class.java)

    fun getPoint(number:Int): @NonNull Single<Response<PointListResponse>>? = provideMyPageApi().getStudentPoint(number)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

}