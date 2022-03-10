package com.dms.pmsandroid.presentation.feature.introduce.ui.activity

import android.os.Bundle
import com.dms.pmsandroid.R
import com.dms.pmsandroid.presentation.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceCompanyBinding
import com.dms.pmsandroid.presentation.feature.introduce.viewmodel.IntroduceCompanyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceCompanyActivity : BaseActivity<ActivityIntroduceCompanyBinding>(R.layout.activity_introduce_company) {

    override val vm: IntroduceCompanyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()
    }

    override fun observeEvent() {
       binding.backImg.setOnClickListener(){
           finish()
       }
    }
}