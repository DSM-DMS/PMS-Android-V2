package com.dms.pmsandroid.data.mypage.remote

import com.dms.pmsandroid.presentation.feature.mypage.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface MyPageApi {

    @GET("/user")
    fun getStudents(@Header("Authorization") accessToken: String): Single<Response<UserListResponse>>

    @POST("/user/student")
    fun studentCertification(
        @Header("Authorization") accessToken: String,
        @Body request: StudentCertificationResponse
    ): Single<Response<Void>>

    @GET("/user/student/{number}")
    fun getStudentInformation(
        @Header("Authorization") accessToken: String,
        @Path("number") number: Int
    ): Single<Response<BasicInformationResponse>>

    @GET("/user/student/outing/{number}")
    fun getStudentOuting(
        @Header("Authorization") accessToken: String,
        @Path("number") number: Int
    ): Single<Response<OutingListResponse>>

    @GET("/user/student/point/{number}")
    fun getStudentPoint(
        @Header("Authorization") accessToken: String,
        @Path("number") number: Int
    ): Single<Response<PointListResponse>>

    @PUT("/user/name")
    fun changeUserName(
        @Header("Authorization") token: String,
        @Body changeNameRequest: ChangeNameRequest
    ): Single<Response<Unit>>

    @HTTP(method = "DELETE", hasBody = true, path = "/user/student")
    fun deleteStudent(
        @Header("Authorization") token: String,
        @Body request: DeleteStudentRequest
    ): Single<Response<Unit>>

}