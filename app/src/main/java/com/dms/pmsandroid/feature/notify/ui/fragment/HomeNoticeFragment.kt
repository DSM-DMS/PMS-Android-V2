package com.dms.pmsandroid.feature.notify.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentHomeNoticeBinding
import com.dms.pmsandroid.feature.notify.adapter.HomeAdapter
import com.dms.pmsandroid.feature.notify.viewmodel.NotifyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeNoticeFragment : BaseFragment<FragmentHomeNoticeBinding>(R.layout.fragment_home_notice) {
    override val vm: NotifyViewModel by sharedViewModel()

    private val homeAdapter by lazy {
        HomeAdapter(vm)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeLayoutManager = LinearLayoutManager(binding.homeRv.context)
        homeLayoutManager.orientation = RecyclerView.VERTICAL
        binding.homeRv.run {
            adapter = homeAdapter
            layoutManager = homeLayoutManager
        }
        vm.getHomeNoticeList(0)
    }
    override fun observeEvent() {
        vm.homeList.observe(viewLifecycleOwner,{
            homeAdapter.setItems(it)
        })
    }
}