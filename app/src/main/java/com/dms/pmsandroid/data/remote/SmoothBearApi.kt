package com.dms.pmsandroid.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SmoothBearApi(okHttpClient: OkHttpClient) {
    val retrofit: Retrofit = Retrofit.Builder().apply {
        baseUrl("http://3.39.33.109:8080")
        client(okHttpClient)
        addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
    }.build()
}