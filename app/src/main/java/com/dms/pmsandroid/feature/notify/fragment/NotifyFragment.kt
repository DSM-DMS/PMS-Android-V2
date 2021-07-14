package com.dms.pmsandroid.feature.notify.fragment

import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentNotifyBinding
import com.dms.pmsandroid.feature.notify.adapter.NotifyAdapter
import com.dms.pmsandroid.feature.notify.viewmodel.NotifyViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotifyFragment : BaseFragment<FragmentNotifyBinding>(R.layout.fragment_notify) {
    override val vm: NotifyViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewpager()
    }

    private fun initViewpager() {
        binding.notifyVp.adapter = NotifyAdapter(requireActivity())

        binding.notifyMainTl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                binding.notifyVp.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.notifyVp.currentItem = tab!!.position
            }

        })
    }

    override fun observeEvent() {
    }
}