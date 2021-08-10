package com.dms.pmsandroid.data.remote.mypage

import com.dms.pmsandroid.feature.mypage.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface MyPageApi {
    @GET("/user")
    fun getStudents( @Header("Authorization") accessToken: String):Single<Response<UserListResponse>>

    @POST("/user/student")
    fun StudentCertification(@Body request : StudentCertificationResponse):Single<Response<Void>>

    @GET("/user/student/{number}")
    fun getStudentInformation(@Path("number") number: Int): Single<Response<BasicInformationResponse>>

    @GET("/user/student/outing/{number}")
    fun getStudentOuting(@Path("number") number: Int):Single<Response<OutingListResponse>>

    @GET("/user/student/point/{number}")
    fun getStudentPoint(@Path("number")number: Int): Single<Response<PointListResponse>>


}