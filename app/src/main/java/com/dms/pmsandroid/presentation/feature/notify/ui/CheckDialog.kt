package com.dms.pmsandroid.presentation.feature.notify.ui

import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.presentation.base.BaseDialog
import com.dms.pmsandroid.databinding.DialogCheckFileViewerBinding
import com.dms.pmsandroid.presentation.feature.notify.viewmodel.NotifyViewModel

class CheckDialog(override val vm: NotifyViewModel) : BaseDialog<DialogCheckFileViewerBinding>(R.layout.dialog_check_file_viewer) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkDownloadTv.setOnClickListener {
            vm.needDownLoad.value = true
        }
        binding.checkCancelTv.setOnClickListener {
            dismiss()
        }
    }
}