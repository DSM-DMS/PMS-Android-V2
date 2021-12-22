package com.dms.pmsandroid.presentation.feature.meal.entity

data class Meal(val breakfast: ArrayList<String>?, val lunch: ArrayList<String>?, val dinner: ArrayList<String>?)

fun ArrayList<String>?.toMealText(): String {
    var mealString = ""
    return if (this != null) {
        for (m in this) {
            mealString += m + "\n"
        }
        if (mealString.isBlank()) {
            "급식이 없습니다"
        } else {
            mealString
        }
    } else {
        "급식이 없습니다"
    }
}
