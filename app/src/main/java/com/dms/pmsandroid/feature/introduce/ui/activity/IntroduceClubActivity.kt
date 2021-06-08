package com.dms.pmsandroid.feature.introduce.ui.activity

import android.os.Bundle
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceClubBinding
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubViewModel

@Suppress("DEPRECATION")
class IntroClubActivity :  BaseActivity<ActivityIntroduceClubBinding>(R.layout.activity_introduce_club) {


    //var data = MutableLiveData<ArrayList<ClubsModel>>()
    lateinit var viewmodel: IntroduceClubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}