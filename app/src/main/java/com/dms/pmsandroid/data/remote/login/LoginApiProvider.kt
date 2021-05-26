package com.dms.pmsandroid.data.remote.login

import com.dms.pmsandroid.data.remote.ApiProvider

fun provideLoginApi():LoginApi=ApiProvider.mRetroFit.create(LoginApi::class.java)