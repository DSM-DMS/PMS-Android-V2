package com.dms.pmsandroid.feature.meal

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

object MealBindingAdapter {
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("meal_time")
    fun mealTime(textView: TextView,time:String){
        textView.text = "${time.substring(0,4)}/${time.substring(4,6)}/${time.substring(6,8)}"
    }
}