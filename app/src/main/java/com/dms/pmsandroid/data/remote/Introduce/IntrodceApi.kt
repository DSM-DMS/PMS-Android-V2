package com.dms.pmsandroid.data.remote

import com.dms.pmsandroid.feature.introduce.model.ClubDetail
import com.dms.pmsandroid.feature.introduce.model.ClubsModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface IntrodceApi {

    @POST("/auth")
    fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Single<Response<Any>>


    //동아리 이름들
    @GET("/introduce/clubs")
    fun club(@Header("Authorization") accessToken: String)
            : Single<Response<ClubsModel>>

    //동아리 상세
    @GET("/introduce/clubs/{clubname}")
    fun clubDetail(
        @Header("Authorization")
        @Path("clubname") accessToken: String
    ): Single<Response<ClubDetail>>


}