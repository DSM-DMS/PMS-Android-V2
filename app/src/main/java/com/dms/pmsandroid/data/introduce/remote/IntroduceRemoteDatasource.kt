package com.dms.pmsandroid.data.introduce.remote

import com.dms.pmsandroid.data.remote.PotatoChipApi
import com.dms.pmsandroid.data.remote.introduce.IntroduceClubApi
import com.dms.pmsandroid.presentation.feature.introduce.model.ClubDetailModel
import com.dms.pmsandroid.presentation.feature.introduce.model.ClubListModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class IntroduceRemoteDatasource(api: PotatoChipApi) {

    private val introduceClubApi = api.retrofit.create(IntroduceClubApi::class.java)

    fun clubApi(accessToken: String): @NonNull Single<Response<ClubListModel>> =
        introduceClubApi.club(accessToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun clubDetailApi(accessToken: String,clubName: String): @NonNull Single<Response<ClubDetailModel>> =
        introduceClubApi.clubDetail(accessToken,clubName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}