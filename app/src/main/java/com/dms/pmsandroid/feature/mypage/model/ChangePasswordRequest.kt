package com.dms.pmsandroid.feature.mypage.model

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("password") val password: String,
    @SerializedName("pre-password") val prePassword: String
)