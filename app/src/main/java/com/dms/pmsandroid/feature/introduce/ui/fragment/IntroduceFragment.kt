package com.dms.pmsandroid.feature.introduce.ui.fragment

import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentIntroduceBinding
import com.dms.pmsandroid.feature.introduce.viewmodel.MainIntroduceViewModel
import com.dms.pmsandroid.ui.MainActivity
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject

class IntroduceFragment : BaseFragment<FragmentIntroduceBinding>(R.layout.fragment_introduce) {

    override val vm: MainIntroduceViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent()
    }

    override fun observeEvent() {
        with(vm) {
            devintroduceClick.observe(viewLifecycleOwner,{
                if(it){
                    (activity as MainActivity).startDeveloper()
                }
                devintroduceClick.value = false
            })
            clubIntroduceClick.observe(viewLifecycleOwner,{
                if(it){
                    (activity as MainActivity).startClub()
                }
                clubIntroduceClick.value = false
            })
            workIntroduceClick.observe(viewLifecycleOwner,{
                if(it){
                    (activity as MainActivity).startCompany()
                }
                workIntroduceClick.value = false
            })
        }
    }
}
