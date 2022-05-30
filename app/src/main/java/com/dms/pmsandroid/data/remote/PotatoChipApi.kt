package com.dms.pmsandroid.data.remote

import com.dms.pmsandroid.data.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PotatoChipApi(okHttpClient: OkHttpClient, authInterceptor: AuthInterceptor) {
    val retrofit: Retrofit = Retrofit.Builder().apply {
        baseUrl("http://3.39.33.109:8080")
        client(okHttpClient.apply {
            newBuilder().addInterceptor(authInterceptor)
        })
        addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
    }.build()
}