package com.dms.pmsandroid.feature.notify.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogNoticeAttachBinding
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NoticeAttachDialog : BaseDialog<DialogNoticeAttachBinding>(R.layout.dialog_notice_attach) {
    override val vm: NoticeDetailViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun observeEvent() {
        vm.noticeDetail.observe(viewLifecycleOwner, {
            if (it.attach.isNotEmpty()) {
                for (att in it.attach) {
                    val layout = NoticeAttachDetail(requireContext())
                    layout.findViewById<TextView>(R.id.notice_attach_tv).text = att.name
                    layout.findViewById<Button>(R.id.notice_attach_btn).setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(att.download))
                        startActivity(intent)
                    }
                    binding.noticeAttachLl.addView(layout)
                }
            }
        })
    }
}