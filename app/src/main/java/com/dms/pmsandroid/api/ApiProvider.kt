package com.dms.pmsandroid.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.Calendar.SECOND
import java.util.concurrent.TimeUnit

object ApiProvider {
    private val BASE_URL: String = "https://api.smooth-bear.live/"
    private val CONNETCT_TIME_OUT: Long = 15
    private val WRITE_TIME_OUT: Long = 15
    private val READ_TIME_OUT: Long = 15
    var okHttpClient: OkHttpClient
    var mRetroFit: Retrofit

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(CONNETCT_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        }.build()
        mRetroFit = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
        }.build()
    }
}