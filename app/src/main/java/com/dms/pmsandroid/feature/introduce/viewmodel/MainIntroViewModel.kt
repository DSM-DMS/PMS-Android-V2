package com.dms.pmsandroid.feature.introduce.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import com.dms.pmsandroid.feature.introduce.ui.activity.IntroduceDeveloperActivity

class MainIntroViewModel {

    fun onClick(view: View, productId: Long) {
        val context: Context = view.getContext()
        val intent = Intent(context, IntroduceDeveloperActivity::class.java)
        context.startActivity(intent)
    }



}