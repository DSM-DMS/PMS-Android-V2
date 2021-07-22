package com.dms.pmsandroid.feature.notify.model

import com.google.gson.annotations.SerializedName

data class GalleryDetailResponse(val gallery:GalleryDetailModel)

data class GalleryDetailModel(val id:Int,@SerializedName("upload-date")val uploadDate:String,val title:String,val body:String,val attach:List<String>,val thumbnail:String)