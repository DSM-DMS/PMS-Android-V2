package com.dms.pmsandroid.feature.notify.ui.activity

import android.os.Bundle
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityNoticeDetailBinding
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeDetailActivity : BaseActivity<ActivityNoticeDetailBinding>(R.layout.activity_notice_detail) {
    override val vm:NoticeDetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("id",0)
        vm.getNoticeDetail(id)
    }

    override fun observeEvent() {

    }
}