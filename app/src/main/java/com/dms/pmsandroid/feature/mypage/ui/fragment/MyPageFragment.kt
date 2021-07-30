package com.dms.pmsandroid.feature.mypage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentMypageBinding
import com.dms.pmsandroid.feature.mypage.ui.MyPageAddStudentDialog
import com.dms.pmsandroid.feature.mypage.viewmodel.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.dms.pmsandroid.ui.MainActivity as MainActivity1

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    override val vm: MyPageViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        observeEvent()

    }

    override fun observeEvent() {
        binding.startAddStudentBtn.setOnClickListener {
            activity.let {
                val dialog = MyPageAddStudentDialog()
                dialog.showsDialog
            }
        }
    }
}