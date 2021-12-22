package com.dms.pmsandroid.presentation.feature.login.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("access-token")val accessToken:String)