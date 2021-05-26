package com.dms.pmsandroid.feature.login.bindingadapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter
import com.dms.pmsandroid.R

object LoginBindingAdapter {
    @SuppressLint("ResourceAsColor")
    @JvmStatic
    @BindingAdapter("check_done")
    fun checkDoneColor(view: View, done:Boolean){
        if(done){
            view.setBackgroundColor(R.color.green)
        }else{
            view.setBackgroundColor(R.color.gray)
        }
    }
}