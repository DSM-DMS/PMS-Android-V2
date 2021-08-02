package com.dms.pmsandroid.feature.notify.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.dms.pmsandroid.R

@SuppressLint("ViewConstructor")
class NoticeAttachDetail(
    context: Context
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.LayoutNoticeAttachBinding
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel

@SuppressLint("ViewConstructor")
class NoticeAttachDetail(
    context: Context,
    viewGroup: ViewGroup,
    viewModel: NoticeDetailViewModel,
    title: String
) :
    LinearLayout(context) {

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_notice_attach, this, true)
        init(context, viewGroup, viewModel, title)
    }

    private fun init(
        context: Context,
        viewGroup: ViewGroup,
        viewModel: NoticeDetailViewModel,
        getTitle: String
    ) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_notice_attach, viewGroup, true)
        val binding = LayoutNoticeAttachBinding.bind(view)
        binding.run {
            vm = viewModel
            title = getTitle
        }
    }
}