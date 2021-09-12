package com.dms.pmsandroid.data.remote.login

import com.dms.pmsandroid.feature.login.model.LoginRequest
import com.dms.pmsandroid.feature.login.model.LoginResponse
import com.dms.pmsandroid.feature.login.model.RegisterRequest
import com.dms.pmsandroid.feature.mypage.model.ChangePasswordRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface LoginApi {
    @POST("/user")
    fun register(@Body request: RegisterRequest): Single<Response<Void>>

    @POST("/auth")
    fun login(@Body request: LoginRequest): Single<Response<LoginResponse>>

    @PUT("/auth/password")
    fun changePassword(@Header("Authorization")token:String,@Body request: ChangePasswordRequest): Single<Response<Void>>
}