package com.dms.pmsandroid.feature.mypage.model

import com.google.gson.annotations.SerializedName

data class BasicInformationResponse(@SerializedName("bonus-point")val bonusPoint:String?, @SerializedName("meal-applied")val mealApplied:Boolean, @SerializedName("minus-point") val minusPoint :String?, @SerializedName("stay-status")val stayStatus:String)
