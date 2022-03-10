package com.dms.pmsandroid.presentation.feature.notify.model

import com.google.gson.annotations.SerializedName

data class GalleryListModel(val galleries: List<GalleryListContent>, @SerializedName("total_page")val totalPage: Int)

data class GalleryListContent(
    val id: Int,
    @SerializedName("upload-date") val uploadDate: String,
    val title: String,
    val thumbnail: String
)