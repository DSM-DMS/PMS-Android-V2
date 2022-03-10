package com.dms.pmsandroid.data.remote.login

import com.dms.pmsandroid.presentation.feature.login.model.LoginRequest
import com.dms.pmsandroid.presentation.feature.login.model.LoginResponse
import com.dms.pmsandroid.presentation.feature.login.model.RegisterRequest
import com.dms.pmsandroid.presentation.feature.mypage.model.ChangePasswordRequest
import com.dms.pmsandroid.presentation.feature.mypage.model.ResetPasswordRequest
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

    @PUT("/password")
    fun changePassword(
        @Header("Authorization") token: String,
        @Body request: ChangePasswordRequest
    ): Single<Response<Void>>

    @POST("/auth/password/reset")
    fun resetPassword(@Body request: ResetPasswordRequest): Single<Response<Void>>
}