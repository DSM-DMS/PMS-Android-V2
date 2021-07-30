package com.dms.pmsandroid.data.remote.mypage

import com.dms.pmsandroid.feature.mypage.model.BasicInformationResponse
import com.dms.pmsandroid.feature.mypage.model.OutingListResponse
import com.dms.pmsandroid.feature.mypage.model.PointListResponse
import com.dms.pmsandroid.feature.mypage.model.StudentCertificationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyPageApi {
    @GET("/user")
    fun getStudents()

    @POST("/user/student")
    fun StudentCertification(@Body request : StudentCertificationResponse):Single<Response<Void>>

    @GET("/user/student/{number}")
    fun getStudentInformation(@Path("number") number: Int): Single<Response<BasicInformationResponse>>

    @GET("/user/student/outing/{number}")
    fun getStudentOuting(@Path("number") number: Int):Single<Response<OutingListResponse>>

    @GET("/user/student/point/{number}")
    fun getStudentPoint(@Path("number")number: Int): Single<Response<PointListResponse>>


}