package com.dms.pmsandroid.feature.introduce.ui.fragment

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentIntroduceBinding
import com.dms.pmsandroid.feature.introduce.viewmodel.MainIntroduceViewModel
import com.dms.pmsandroid.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceFragment : BaseFragment<FragmentIntroduceBinding>(R.layout.fragment_introduce) {

    override val vm: MainIntroduceViewModel by viewModel()

    override fun observeEvent() {
        vm.run {
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
