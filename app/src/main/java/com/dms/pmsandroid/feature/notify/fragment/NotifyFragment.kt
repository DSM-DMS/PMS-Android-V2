package com.dms.pmsandroid.feature.notify.fragment

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentNotifyBinding
import com.dms.pmsandroid.feature.notify.viewmodel.NotifyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotifyFragment : BaseFragment<FragmentNotifyBinding>(R.layout.fragment_notify) {
    override val vm : NotifyViewModel by viewModel()

    override fun observeEvent() {
        
    }
}