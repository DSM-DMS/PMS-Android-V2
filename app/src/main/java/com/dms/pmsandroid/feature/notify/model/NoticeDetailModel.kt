package com.dms.pmsandroid.feature.notify.model

import com.google.gson.annotations.SerializedName

data class NoticeDetailModel(
    val id: Int,
    @SerializedName("upload-date") val uploadDate: String,
    val title: String,
    val body: String,
    val writer: String?,
    val attach: List<DetailAttach>,
    val comment:List<CommentModel>
)

data class DetailAttach(val download: String, val name: String)