package com.dms.pmsandroid.feature.introduce.ui.fragment

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentIntroduceBinding
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceDeveloperFragment :
    BaseFragment<FragmentIntroduceBinding>(R.layout.fragment_introduce_developer) {
    override val vm: IntroduceDeveloperViewModel
            by viewModel()

    override fun observeEvent() {

    }


}