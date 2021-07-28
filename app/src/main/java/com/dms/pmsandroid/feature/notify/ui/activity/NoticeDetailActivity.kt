package com.dms.pmsandroid.feature.notify.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityNoticeDetailBinding
import com.dms.pmsandroid.feature.notify.adapter.NoticeDetailAdapter
import com.dms.pmsandroid.feature.notify.ui.NoticeAttachDialog
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeDetailActivity :
    BaseActivity<ActivityNoticeDetailBinding>(R.layout.activity_notice_detail) {
    override val vm: NoticeDetailViewModel by viewModel()

    private val noticeAdapter by lazy {
        NoticeDetailAdapter(vm)
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

        val noticeLayoutManager = LinearLayoutManager(binding.noticeDetailRv.context)
        noticeLayoutManager.orientation = RecyclerView.VERTICAL

        binding.noticeDetailRv.run {
            adapter = noticeAdapter
            layoutManager = noticeLayoutManager
        }

        binding.noticeDetailTitleTv.text = title
        binding.noticeBackBtn.setOnClickListener {
            finish()
        }
        vm.getNoticeDetail(id)
    }

    private val dialog = NoticeAttachDialog()
    override fun observeEvent() {
        vm.noticeDetail.observe(this, {
            noticeAdapter.setItems(it.comment)
        })
        vm.attachClicked.observe(this, {
            if (it) {
                dialog.show(supportFragmentManager, "AttachDialogFragment")
                vm.attachClicked.value = false
            }
        })
        vm.doneReComments.observe(this, {
            if (it) {
                vm.doneReComments.value = false
                noticeAdapter.notifyDataSetChanged()
            }
        })
        vm.resetComments.observe(this, {
            keyBoardManager.hideSoftInputFromWindow(binding.noticeDetailEt.windowToken,0)
            doneInput = HashMap()
        })
        vm.clickedCommentId.observe(this,{
            if(it!=null){
                binding.noticeDetailEt.requestFocus()
                keyBoardManager.showSoftInput(binding.noticeDetailEt,0)
            }
        })
    }
}