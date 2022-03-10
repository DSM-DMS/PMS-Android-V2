package com.dms.pmsandroid.presentation.feature.notify.ui.fragment

import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.presentation.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentNotifyBinding
import com.dms.pmsandroid.presentation.feature.notify.adapter.NotifyAdapter
import com.dms.pmsandroid.presentation.feature.notify.viewmodel.NotifyViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotifyFragment : BaseFragment<FragmentNotifyBinding>(R.layout.fragment_notify) {
    override val vm: NotifyViewModel by viewModel()

    private val titleList = arrayListOf("포토앨범","공지사항","가정통신문")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewpager()
    }

    private fun initViewpager() {
        binding.notifyVp.run {
            adapter = NotifyAdapter(requireActivity())
            isUserInputEnabled = false
        }
        TabLayoutMediator(binding.notifyMainTl, binding.notifyVp) { tab, position ->
            binding.notifyVp.currentItem = binding.notifyMainTl.selectedTabPosition
            tab.text = titleList[position]
        }.attach()
    }

    override fun observeEvent() {
    }
}