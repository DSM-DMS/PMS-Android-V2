package com.dms.pmsandroid.presentation.feature.notify.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CommentModel(
    val id: Int,
    @SerializedName("upload-date") val uploadDate: Date,
    val body: String,
    val user: CommentUserModel?,
    var doneInput:Boolean
)