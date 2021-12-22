package com.dms.pmsandroid.presentation.feature.meal.bindingadapter

import android.annotation.SuppressLint
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SetTextI18n")
@BindingAdapter("meal_time")
fun mealTime(textView: TextView, time: LocalDate) {
    textView.text = "${time.year}/${String.format("%02d", time.monthValue)}/${String.format("%02d", time.dayOfMonth)}"
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("week_time")
fun weekTime(textView: TextView, week: LocalDate) {
    when (week.dayOfWeek.value) {
        1 -> textView.text = "월요일"
        2 -> textView.text = "화요일"
        3 -> textView.text = "수요일"
        4 -> textView.text = "묙요일"
        5 -> textView.text = "금요일"
        6 -> textView.text = "토요일"
        7 -> textView.text = "일요일"
    }
}

@BindingAdapter("view_pager_adapter")
fun recyclerViewHorAdapter(
    viewPager: ViewPager2,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?
) {
    if (adapter != null) {
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.adapter = adapter
    }
}