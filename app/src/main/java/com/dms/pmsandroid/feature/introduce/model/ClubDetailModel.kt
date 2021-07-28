package com.dms.pmsandroid.feature.introduce.model

data class ClubDetailResponse(val clubDetail: ClubDetailModel)

data class ClubDetailModel(
    val title: String?,
    val url: String?,
    val explanation: String?,
    val member: List<String>?
)