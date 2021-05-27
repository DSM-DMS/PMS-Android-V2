package com.dms.pmsandroid.data.remote.login

import com.dms.pmsandroid.data.remote.ApiProvider
import com.dms.pmsandroid.feature.login.model.LoginRequest
import com.dms.pmsandroid.feature.login.model.RegisterRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class LoginApiProvider{
    private fun provideLoginApi():LoginApi=ApiProvider.mRetroFit.create(LoginApi::class.java)

    fun registerApi(request: RegisterRequest):@NonNull Single<Response<Any>> = provideLoginApi().register(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun loginApi(request: LoginRequest):@NonNull Single<Response<Any>> = provideLoginApi().login(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}
