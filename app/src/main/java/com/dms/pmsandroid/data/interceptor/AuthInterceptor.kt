package com.dms.pmsandroid.data.interceptor

import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sharedPreferenceStorage: SharedPreferenceStorage): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization",sharedPreferenceStorage.getInfo("access_token"))
            .build()
        return chain.proceed(request)
    }
}