package com.dms.pmsandroid.presentation.feature.mypage.model

import com.google.gson.annotations.SerializedName

data class ChangeNameRequest(@SerializedName("name")val name : String)