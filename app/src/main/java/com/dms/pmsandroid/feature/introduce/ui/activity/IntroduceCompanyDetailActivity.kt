package com.dms.pmsandroid.feature.introduce.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityCompanyDetailBinding
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubDetailViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceCompanyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceCompanyDetailActivity : BaseActivity<ActivityCompanyDetailBinding>(R.layout.activity_club__detail)
{
    override val vm : IntroduceCompanyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun observeEvent() {
        TODO("Not yet implemented")
    }

}