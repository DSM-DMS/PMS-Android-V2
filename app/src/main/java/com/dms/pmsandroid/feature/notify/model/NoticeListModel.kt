package com.dms.pmsandroid.feature.notify.model

import com.google.gson.annotations.SerializedName

data class NoticeListModel(val id:Int,@SerializedName("upload-date")val uploadDate:String,val title:String,val writer:String)