package com.dms.pmsandroid.feature.notify.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogNoticeAttachBinding
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NoticeAttachDialog : BaseDialog<DialogNoticeAttachBinding>(R.layout.dialog_notice_attach) {
    override val vm: NoticeDetailViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun observeEvent() {
        vm.noticeDetail.observe(viewLifecycleOwner,{
            if(it.attach.isNotEmpty()){
                for(att in it.attach){
                    val layout = NoticeAttachDetail(requireContext(),binding.noticeAttachLl,vm,att.name)
                    binding.noticeAttachLl.addView(layout)
                }
            }
        })
    }
}