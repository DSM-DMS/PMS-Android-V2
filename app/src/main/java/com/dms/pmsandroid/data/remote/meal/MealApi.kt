package com.dms.pmsandroid.data.remote.meal

import retrofit2.http.GET
import retrofit2.http.Header

interface MealApi {
    @GET("/event/meal/{datetime}")
    fun getMeal(@Header("Authorization") accessToken: String):Single<Response<>>
}