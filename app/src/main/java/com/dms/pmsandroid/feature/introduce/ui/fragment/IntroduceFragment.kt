package com.dms.pmsandroid.feature.introduce.ui.fragment

import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentIntroduceBinding
import com.dms.pmsandroid.feature.introduce.viewmodel.MainIntroViewModel
import com.dms.pmsandroid.ui.MainActivity
import org.koin.android.ext.android.inject

class IntroduceFragment : BaseFragment<FragmentIntroduceBinding>(R.layout.fragment_introduce) {

    private val vm : MainIntroViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = vm
        setClickEvent()
    }

    private fun setClickEvent(){
        binding.introDevBtn.setOnClickListener {
            (activity as MainActivity).startDeveloper()
        }
        binding.introClubBtn.setOnClickListener {
            (activity as MainActivity).startClub()
        }
        binding.introWorkBtn.setOnClickListener {
            (activity as MainActivity).startCompany()
        }
    }
}
