package com.dms.pmsandroid.feature.notify.model

import com.google.gson.annotations.SerializedName

data class GalleryListModel(val galleries: List<GalleryListContent>, val totalLength: Int)

data class GalleryListContent(
    val id: Int,
    @SerializedName("upload-date") val uploadDate: String,
    val title: String,
    val thumbnail: String
)