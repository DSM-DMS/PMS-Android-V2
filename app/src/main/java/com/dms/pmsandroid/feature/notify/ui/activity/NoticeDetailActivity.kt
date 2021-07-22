package com.dms.pmsandroid.feature.notify.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityNoticeDetailBinding
import com.dms.pmsandroid.feature.notify.adapter.NoticeDetailAdapter
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeDetailActivity : BaseActivity<ActivityNoticeDetailBinding>(R.layout.activity_notice_detail) {
    override val vm:NoticeDetailViewModel by viewModel()

    private val noticeAdapter by lazy {
        NoticeDetailAdapter(vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra("id",0)
        val title = intent.getStringExtra("title")
        vm.getNoticeDetail(id)

        val noticeLayoutManager = LinearLayoutManager(binding.noticeDetailRv.context)
        noticeLayoutManager.orientation = RecyclerView.VERTICAL

        binding.noticeDetailRv.run{
            adapter = noticeAdapter
            layoutManager = noticeLayoutManager
        }

        binding.noticeDetailTitleTv.text = title
        binding.noticeBackBtn.setOnClickListener {
            finish()
        }
    }

    override fun observeEvent() {
        vm.noticeDetail.observe(this,{
            noticeAdapter.notifyDataSetChanged()
        })
    }
}