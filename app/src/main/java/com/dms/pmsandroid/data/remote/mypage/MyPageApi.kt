package com.dms.pmsandroid.data.remote.mypage

import retrofit2.http.GET

interface MyPageApi {
    @GET("/user")
    fun getStudents()

    @GET("/user/student/{number}")
    fun getStudentPoint(){

    }
}