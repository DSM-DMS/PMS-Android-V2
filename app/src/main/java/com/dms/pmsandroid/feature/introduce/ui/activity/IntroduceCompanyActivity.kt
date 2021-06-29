package com.dms.pmsandroid.feature.introduce.ui.activity

import android.os.Bundle
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceCompanyBinding
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceCompanyViewModel

@Suppress("DEPRECATION")
class IntroduceCompanyActivity :  BaseActivity<ActivityIntroduceCompanyBinding>(R.layout.activity_introduce_company)  {

    lateinit var viewmodel: IntroduceCompanyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}