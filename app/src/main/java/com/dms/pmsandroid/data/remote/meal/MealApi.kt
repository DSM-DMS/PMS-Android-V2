package com.dms.pmsandroid.data.remote.meal

import com.dms.pmsandroid.feature.meal.model.MealResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MealApi {
    @GET("/event/meal/{datetime}")
    fun getMeal(@Header("Authorization") accessToken: String,@Path("datetime")time:String): Single<Response<MealResponse>>
}