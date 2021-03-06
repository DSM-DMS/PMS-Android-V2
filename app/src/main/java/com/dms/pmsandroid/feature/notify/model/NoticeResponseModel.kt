package com.dms.pmsandroid.feature.notify.model

import com.google.gson.annotations.SerializedName

data class NoticeResponseModel(
    val notices: List<NoticeListModel>,
    @SerializedName("total_page") val totalPage: Int
)