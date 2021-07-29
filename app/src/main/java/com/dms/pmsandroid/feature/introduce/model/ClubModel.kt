package com.dms.pmsandroid.feature.introduce.model

import com.google.gson.annotations.SerializedName


data class ClubModel(@SerializedName("club-name")val clubName: String?, @SerializedName("picture-uri")val pictureUrl: String?)
