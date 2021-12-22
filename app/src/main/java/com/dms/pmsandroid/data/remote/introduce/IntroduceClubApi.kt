package com.dms.pmsandroid.data.remote.introduce


import com.dms.pmsandroid.presentation.feature.introduce.model.ClubDetailModel
import com.dms.pmsandroid.presentation.feature.introduce.model.ClubListModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface IntroduceClubApi {
    @GET("/introduce/clubs")
    fun club(
            @Header("Authorization") accessToken: String
    ): Single<Response<ClubListModel>>


    @GET("/introduce/clubs/{clubname}")
    fun clubDetail(
            @Header("Authorization")accessToken: String,
            @Path("clubname")clubname:String
    ): Single<Response<ClubDetailModel>>
}