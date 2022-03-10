package com.dms.pmsandroid.data.meal.remote

import com.dms.pmsandroid.presentation.feature.meal.model.MealPictureResponse
import com.dms.pmsandroid.presentation.feature.meal.model.MealResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MealApi {
    @GET("/event/meal/{datetime}")
    fun getMeal(@Header("Authorization") accessToken: String,@Path("datetime")time:String): Single<Response<MealResponse>>

    @GET("/event/meal/picture/{datetime}")
    fun getMealPicture(@Header("Authorization") accessToken: String,@Path("datetime")time:String): Single<Response<MealPictureResponse>>
}