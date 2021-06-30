package com.dms.pmsandroid.feature.meal.bindingadapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dms.pmsandroid.R

object MealBindingAdapter {
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("meal_time")
    fun mealTime(textView: TextView,time:String){
        textView.text = "${time.substring(0,4)}/${time.substring(4,6)}/${time.substring(6,8)}"
    }

    @JvmStatic
    @BindingAdapter("week_time")
    fun weekTime(textView:TextView,week:Int){
        when(week){
            1->textView.text = "월요일"
            2->textView.text = "화요일"
            3->textView.text = "수요일"
            4->textView.text = "묙요일"
            5->textView.text = "금요일"
            6->textView.text = "토요일"
            7->textView.text = "일요일"
        }
    }

    @JvmStatic
    @BindingAdapter("view_pager_adapter")
    fun recyclerViewHorAdapter(viewPager: ViewPager2, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?){
        if(adapter!=null){
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            viewPager.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter("glide_meal_image_load")
    fun glideMealImageLoad(imageView: ImageView, resource: String?) {
        val circularProgressDrawable = CircularProgressDrawable(imageView.context)
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 40f
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(imageView.context, R.color.blue), PorterDuff.Mode.SRC_IN )
        circularProgressDrawable.start()

        Glide.with(imageView.context)
            .load(resource)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.img_no_picture)
            .into(imageView)
    }

}