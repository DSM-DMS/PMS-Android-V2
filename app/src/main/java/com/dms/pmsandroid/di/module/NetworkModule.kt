package com.dms.pmsandroid.di.module

import android.util.Log
import com.dms.pmsandroid.BuildConfig
import com.dms.pmsandroid.data.interceptor.AuthInterceptor
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rxdogtag2.RxDogTag
import java.util.concurrent.TimeUnit

val networkModule = module {

    RxDogTag.install()
    RxJavaPlugins.setErrorHandler { Log.d("RxError", it.toString()) }

    val BASE_URL = "https://api."
    val CONNECT_TIME_OUT: Long = 15
    val WRITE_TIME_OUT: Long = 15
    val READ_TIME_OUT: Long = 15

    single {
        HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG){
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        OkHttpClient().newBuilder().apply {
            addInterceptor(get<HttpLoggingInterceptor>())
            addInterceptor(get<AuthInterceptor>())
            connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        }.build()
    }

    factory { AuthInterceptor(get()) }

    single {
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(get<OkHttpClient>())
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
}

