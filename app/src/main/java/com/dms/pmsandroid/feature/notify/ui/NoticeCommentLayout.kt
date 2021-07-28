package com.dms.pmsandroid.feature.notify.ui

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dms.pmsandroid.R

class NoticeCommentLayout(context: Context):ConstraintLayout(context) {
    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_re_comment_notice,this,true)
    }
}