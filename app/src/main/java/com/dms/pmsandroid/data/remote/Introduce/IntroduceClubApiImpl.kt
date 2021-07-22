package com.dms.pmsandroid.data.remote.Introduce

import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.feature.introduce.model.ClubDetailModel
import com.dms.pmsandroid.feature.introduce.model.ClubListModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class IntroduceClubApiImpl {
    private fun providerIntroduceClubApi(): IntroduceClubApi = ApiProvider.jiWooRetrofitBuilder.create(
        IntroduceClubApi::class.java)

    fun clubApi(accessToken: String): @NonNull Single<Response<ClubListModel>> = providerIntroduceClubApi().club(accessToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun clubDetailApi(accessToken: String,clubname: String): @NonNull Single<Response<ClubDetailModel>> = providerIntroduceClubApi().clubDetail(accessToken, clubname)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


}