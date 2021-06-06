package com.dms.pmsandroid.feature.introduce.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceClubBinding
import com.dms.pmsandroid.databinding.ActivityIntroduceDeveloperBinding
import com.dms.pmsandroid.feature.introduce.adapter.DeveloperAdapter
import com.dms.pmsandroid.feature.introduce.model.ClubsModel
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceWorkViewModel

@Suppress("DEPRECATION")
class IntroClubActivity :  BaseActivity<ActivityIntroduceClubBinding>(R.layout.activity_introduce_club) {


    //var data = MutableLiveData<ArrayList<ClubsModel>>()
    lateinit var viewmodel: IntroduceClubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}