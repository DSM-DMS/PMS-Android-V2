package com.dms.pmsandroid.feature.notify.ui.activity

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.base.EventObserver
import com.dms.pmsandroid.databinding.ActivityNoticeDetailBinding
import com.dms.pmsandroid.feature.notify.adapter.NoticeDetailAdapter
import com.dms.pmsandroid.feature.notify.ui.NoticeAttachDialog
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeDetailActivity :
    BaseActivity<ActivityNoticeDetailBinding>(R.layout.activity_notice_detail) {
    override val vm: NoticeDetailViewModel by viewModel()

    private val noticeAdapter by lazy {
        NoticeDetailAdapter(vm, this)
    }

    companion object {
        lateinit var doneInput: HashMap<Int, Boolean>
    }

    private lateinit var keyBoardManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doneInput = HashMap()
        keyBoardManager = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")

        vm.noticeId = id

        val noticeLayoutManager = LinearLayoutManager(binding.noticeDetailRv.context)
        noticeLayoutManager.orientation = RecyclerView.VERTICAL

        binding.run {
            noticeDetailRv.run {
                recycledViewPool.setMaxRecycledViews(1, 0)
                adapter = noticeAdapter
                layoutManager = noticeLayoutManager
            }
            noticeDetailTitleTv.text = title
            noticeBackBtn.setOnClickListener {
                finish()
            }
        }
        vm.loadNoticeDetail()
    }

    private val dialog = NoticeAttachDialog()

    fun showContent() {
        dialog.show(supportFragmentManager, "AttachDialogFragment")
    }

    override fun observeEvent() {
        vm.run {
            noticeDetail.observe(this@NoticeDetailActivity, {
                noticeAdapter.setItems(it.comment)
            })

            doneReComments.observe(this@NoticeDetailActivity, {
                noticeAdapter.notifyItemChanged(it)
            })

            resetComments.observe(this@NoticeDetailActivity, {
                keyBoardManager.hideSoftInputFromWindow(binding.noticeDetailEt.windowToken, 0)
                binding.noticeDetailEt.text = null
                doneInput = HashMap()
            })

            clickedCommentId.observe(this@NoticeDetailActivity, EventObserver {
                binding.noticeDetailEt.requestFocus()
                keyBoardManager.showSoftInput(binding.noticeDetailEt, 0)
            })
        }
    }
}