package com.dms.pmsandroid.feature.login.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("access-token")val accessToken:String)