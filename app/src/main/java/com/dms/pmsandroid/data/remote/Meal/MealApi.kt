package com.dms.pmsandroid.data.remote.Meal

import com.dms.pmsandroid.feature.calendar.model.SchedulesResponse
import com.dms.pmsandroid.feature.meal.model.MealModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MealApi {
    @GET("/event/meal/{datetime}")
    fun addMeal(@Header("Authorization") accessToken: String):Single<Response<MealModel>>
}