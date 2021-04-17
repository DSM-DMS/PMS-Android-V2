package com.dms.pmsandroid.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService{
    @POST("/auth")
    fun login(@Query("email") email:String,@Query("password")password:String):Single<Response<Any>>
}