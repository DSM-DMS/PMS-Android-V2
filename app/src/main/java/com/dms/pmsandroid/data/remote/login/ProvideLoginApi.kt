package com.dms.pmsandroid.data.remote.login

import com.dms.pmsandroid.data.remote.SmoothBearApi
import com.dms.pmsandroid.presentation.feature.login.model.LoginRequest
import com.dms.pmsandroid.presentation.feature.login.model.LoginResponse
import com.dms.pmsandroid.presentation.feature.login.model.RegisterRequest
import com.dms.pmsandroid.presentation.feature.mypage.model.ChangePasswordRequest
import com.dms.pmsandroid.presentation.feature.mypage.model.ResetPasswordRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class ProvideLoginApi(api: SmoothBearApi) {

    private val loginApi = api.retrofit.create(LoginApi::class.java)

    fun registerApi(request: RegisterRequest): @NonNull Single<Response<Void>> =
        loginApi.register(request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun loginApi(request: LoginRequest): @NonNull Single<Response<LoginResponse>> =
        loginApi.login(request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun changePassword(
        token: String,
        request: ChangePasswordRequest
    ): @NonNull Single<Response<Void>> =
        loginApi.changePassword(token, request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun resetPassword(request: ResetPasswordRequest): @NonNull Single<Response<Void>> =
        loginApi.resetPassword(request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}
