package com.dms.pmsandroid.data.remote


import com.dms.pmsandroid.feature.calendar.model.SchedulesResponse
import com.dms.pmsandroid.feature.introduce.model.ClubDetailModel
import com.dms.pmsandroid.feature.introduce.model.ClubModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface IntroduceApi {

    @GET("/introduce/clubs")
    fun club(@Header("Authorization") accessToken: String):Single<Response<ClubModel>>


    @GET("/introduce/clubs/{clubname}")
    fun clubDetail(
            @Header("Authorization")
            @Path("clubname") accessToken: String
    ): Single<Response<ClubDetailModel>>

    @GET("/calendar")
    fun schedules(@Header("Authorization") accessToken: String): io.reactivex.rxjava3.core.Single<Response<SchedulesResponse>>


}