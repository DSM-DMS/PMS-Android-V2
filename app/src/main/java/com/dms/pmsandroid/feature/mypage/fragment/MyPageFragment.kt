package com.dms.pmsandroid.feature.mypage.fragment

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentMypageBinding
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {


    override val vm: MyPageViewModel by viewModel()

    override fun observeEvent() {
    }
}