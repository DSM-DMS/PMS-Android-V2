package com.dms.pmsandroid.feature.mypage.ui.activity

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityOutingContentBinding
import com.dms.pmsandroid.feature.mypage.viewmodel.OutingContentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingContentActivity : BaseActivity<ActivityOutingContentBinding>(R.layout.activity_outing_content) {

    override val vm : OutingContentViewModel by viewModel()

    override fun observeEvent() {
        TODO("Not yet implemented")
    }
}