package com.dms.pmsandroid.feature.mypage.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.dms.pmsandroid.R

class StudentInflater(context:Context):LinearLayout(context) {
    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_student,this,true)
    }
}