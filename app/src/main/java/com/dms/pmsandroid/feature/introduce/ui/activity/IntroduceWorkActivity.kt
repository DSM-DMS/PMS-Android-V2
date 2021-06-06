package com.dms.pmsandroid.feature.introduce.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceDeveloperBinding
import com.dms.pmsandroid.databinding.ActivityIntroduceWorkBinding
import com.dms.pmsandroid.feature.introduce.adapter.DeveloperAdapter
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceWorkViewModel

@Suppress("DEPRECATION")
class IntroduceWorkActivity :  BaseActivity<ActivityIntroduceWorkBinding>(R.layout.activity_introduce_work)  {

    lateinit var viewmodel: IntroduceWorkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        
    }

}