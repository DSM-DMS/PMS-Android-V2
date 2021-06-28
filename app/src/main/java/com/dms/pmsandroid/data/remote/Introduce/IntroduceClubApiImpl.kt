package com.dms.pmsandroid.data.remote.Introduce

import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.data.remote.IntroduceClubApi
import com.dms.pmsandroid.feature.login.model.LoginResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class IntroduceClubApiProvider {
    private fun providerIntroduceClubApi(): IntroduceClubApi = ApiProvider.jiWooRetrofitBuilder.create(IntroduceClubApi::class.java)

    fun clubApi(): @NonNull Single<Response<ClubResponse>> = providerIntroduceClubApi().club()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun clubDetailApi(): @NonNull Single<Response<LoginResponse>> = providerIntroduceClubApi().clubDetail()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}