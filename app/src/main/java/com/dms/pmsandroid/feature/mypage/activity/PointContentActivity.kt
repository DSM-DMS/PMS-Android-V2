package com.dms.pmsandroid.feature.mypage.activity

import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityPointContentBinding
import com.dms.pmsandroid.feature.mypage.viewmodel.PointContentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PointContentActivity: BaseActivity<ActivityPointContentBinding>(R.layout.activity_point_content) {

    override val vm : PointContentViewModel by viewModel()

    override fun observeEvent() {
        TODO("Not yet implemented")
    }

}