package com.dms.pmsandroid.data.remote

import com.dms.pmsandroid.feature.introduce.model.ClubsModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    //동아리 이름들
    @GET("/introduce/clubs")
    fun club(@Header("Authorization") accessToken: String)
    : Single<Response<ClubsModel>>




}