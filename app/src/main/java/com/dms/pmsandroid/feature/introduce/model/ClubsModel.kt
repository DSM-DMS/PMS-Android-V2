package com.dms.pmsandroid.feature.introduce.model

data class ClubsModel(val clubs : List<String>)

data class ClubDetail(val title : String,val url : String, val explanation : String, val member : List<String> )
