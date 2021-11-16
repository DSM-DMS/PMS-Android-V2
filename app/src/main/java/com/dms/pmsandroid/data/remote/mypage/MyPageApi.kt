package com.dms.pmsandroid.data.remote.mypage

import com.dms.pmsandroid.feature.mypage.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface MyPageApi {
    @GET("smooth-bear.live/user")
    fun getStudents(@Header("Authorization") accessToken: String): Single<Response<UserListResponse>>

    @POST("smooth-bear.live/user/student")
    fun studentCertification(
        @Header("Authorization") accessToken: String,
        @Body request: StudentCertificationResponse
    ): Single<Response<Void>>

    @GET("smooth-bear.live/user/student/{number}")
    fun getStudentInformation(
        @Header("Authorization") accessToken: String,
        @Path("number") number: Int
    ): Single<Response<BasicInformationResponse>>

    @GET("smooth-bear.live/user/student/outing/{number}")
    fun getStudentOuting(
        @Header("Authorization") accessToken: String,
        @Path("number") number: Int
    ): Single<Response<OutingListResponse>>

    @GET("smooth-bear.live/user/student/point/{number}")
    fun getStudentPoint(
        @Header("Authorization") accessToken: String,
        @Path("number") number: Int
    ): Single<Response<PointListResponse>>

    @PUT("smooth-bear.live/user/name")
    fun changeUserName(
        @Header("Authorization") token: String,
        @Body changeNameRequest: ChangeNameRequest
    ): Single<Response<Unit>>

    @HTTP(method = "DELETE", hasBody = true, path = "smooth-bear.live/user/student")
    fun deleteStudent(
        @Header("Authorization") token: String,
        @Body request: DeleteStudentRequest
    ): Single<Response<Unit>>

}