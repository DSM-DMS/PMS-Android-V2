package com.dms.pmsandroid.feature.mypage.model

import com.google.gson.annotations.SerializedName

data class StudentResponse(@SerializedName("student-name")val studentName :String, @SerializedName("student-number")val studentNumber:Int)
