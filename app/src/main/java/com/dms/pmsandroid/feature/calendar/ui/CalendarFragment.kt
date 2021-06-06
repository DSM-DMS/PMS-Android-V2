package com.dms.pmsandroid.feature.calendar.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentCalendarBinding
import com.dms.pmsandroid.feature.calendar.viewmodel.CalendarViewModel
import com.dms.pmsandroid.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val vm : CalendarViewModel by viewModel()
    private val mainVm : MainViewModel by sharedViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        observeToken()
    }

    private fun observeToken(){
        mainVm.doneToken.observe(viewLifecycleOwner, Observer{
            if(it){
                vm.loadSchedules()
            }
        })
    }

}