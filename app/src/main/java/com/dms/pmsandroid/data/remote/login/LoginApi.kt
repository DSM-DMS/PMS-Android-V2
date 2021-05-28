package com.dms.pmsandroid.data.remote.login

import com.dms.pmsandroid.feature.login.model.LoginRequest
import com.dms.pmsandroid.feature.login.model.LoginResponse
import com.dms.pmsandroid.feature.login.model.RegisterRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("/user")
    fun register(@Body request: RegisterRequest): Single<Response<Any>>

    @POST("/auth")
    fun login(@Body request: LoginRequest): Single<Response<LoginResponse>>
}