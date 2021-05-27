package com.dms.pmsandroid.data.remote

import android.util.Log
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import rxdogtag2.RxDogTag
import java.util.concurrent.TimeUnit

object ApiProvider {
    private val BASE_URL: String = "https://api.smooth-bear.live"
    private val CONNETCT_TIME_OUT: Long = 15
    private val WRITE_TIME_OUT: Long = 15
    private val READ_TIME_OUT: Long = 15
    private var okHttpClient: OkHttpClient
    var mRetroFit: Retrofit
    private var retrofitInterFace:ApiService

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
            client(okHttpClient)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        retrofitInterFace = mRetroFit.create()
        RxDogTag.install()
        RxJavaPlugins.setErrorHandler { Log.d("RxError", it.toString()) }

    }
    fun getInstance(): ApiService{
        return retrofitInterFace
    }

}