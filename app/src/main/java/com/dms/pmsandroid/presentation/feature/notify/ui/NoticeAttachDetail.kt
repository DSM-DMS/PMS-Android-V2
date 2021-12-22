package com.dms.pmsandroid.presentation.feature.notify.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.dms.pmsandroid.R

@SuppressLint("ViewConstructor")
class NoticeAttachDetail(
    context: Context
) :
    LinearLayout(context) {

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_notice_attach, this, true)
    }
}