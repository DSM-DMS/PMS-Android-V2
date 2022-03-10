package com.dms.pmsandroid.presentation.feature.notify.model

import com.google.gson.annotations.SerializedName

data class CommentUserModel(
    val email: String,
    val name: String,
    @SerializedName("user_role") val userRole: String
)