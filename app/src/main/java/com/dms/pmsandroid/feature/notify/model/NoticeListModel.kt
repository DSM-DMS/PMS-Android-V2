package com.dms.pmsandroid.feature.notify.model

import com.google.gson.annotations.SerializedName

data class NoticeListModel(val id:String,@SerializedName("upload-date")val uploadDate:String,val title:String,val writer:String)