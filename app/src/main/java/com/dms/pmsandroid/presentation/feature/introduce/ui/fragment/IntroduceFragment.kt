package com.dms.pmsandroid.presentation.feature.introduce.ui.fragment

import android.os.Bundle
import android.view.View
import com.dms.pmsandroid.R
import com.dms.pmsandroid.presentation.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentIntroduceBinding
import com.dms.pmsandroid.presentation.feature.introduce.viewmodel.MainIntroduceViewModel
import com.dms.pmsandroid.presentation.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceFragment : BaseFragment<FragmentIntroduceBinding >(R.layout.fragment_introduce) {

    override val vm: MainIntroduceViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.introduceWorkCv.setOnClickListener {
            (activity as MainActivity).startCompany()
        }
        binding.introduceDevCv.setOnClickListener {
            (activity as MainActivity).startDeveloper()
        }
        binding.introduceClubCv.setOnClickListener {
            (activity as MainActivity).startClub()
        }
    }

    override fun observeEvent() {
    }
}
