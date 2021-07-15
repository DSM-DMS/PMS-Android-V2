package com.dms.pmsandroid.feature.notify.ui.fragment

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentHomeNoticeBinding
import com.dms.pmsandroid.feature.notify.viewmodel.NotifyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeNoticeFragment : BaseFragment<FragmentHomeNoticeBinding>(R.layout.fragment_home_notice) {
    override val vm: NotifyViewModel by sharedViewModel()
    override fun observeEvent() {

    }
}