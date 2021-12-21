package com.dms.pmsandroid.data.calendar.dto

import com.google.gson.annotations.SerializedName

data class CalendarResponse(
    @SerializedName("1")
    val janEvents: Map<String, List<String>>,

    @SerializedName("2")
    val febEvents: Map<String, List<String>>,

    @SerializedName("3")
    val marEvents: Map<String, List<String>>,

    @SerializedName("4")
    val aprEvents: Map<String, List<String>>,

    @SerializedName("5")
    val mayEvents: Map<String, List<String>>,

    @SerializedName("6")
    val junEvents: Map<String, List<String>>,

    @SerializedName("7")
    val julEvents: Map<String, List<String>>,

    @SerializedName("8")
    val augEvents: Map<String, List<String>>,

    @SerializedName("9")
    val sepEvents: Map<String, List<String>>,

    @SerializedName("10")
    val octEvents: Map<String, List<String>>,

    @SerializedName("11")
    val novEvents: Map<String, List<String>>,

    @SerializedName("12")
    val decEvents: Map<String, List<String>>
)