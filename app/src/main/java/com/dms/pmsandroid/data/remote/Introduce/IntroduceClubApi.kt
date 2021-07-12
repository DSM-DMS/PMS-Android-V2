package com.dms.pmsandroid.data.remote


import com.dms.pmsandroid.feature.introduce.model.ClubDetailModel
import com.dms.pmsandroid.feature.introduce.model.ClubModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface IntroduceClubApi {

    @GET("/introduce/clubs")
    fun club(
            @Header("Authorization") accessToken: String
    ): Single<Response<List<ClubModel>>>


    @GET("/introduce/clubs/{clubname}")
    fun clubDetail(
            @Header("Authorization")accessToken: String,
            @Path("clubname") clubName : String
    ): Single<Response<ClubDetailModel>>



}