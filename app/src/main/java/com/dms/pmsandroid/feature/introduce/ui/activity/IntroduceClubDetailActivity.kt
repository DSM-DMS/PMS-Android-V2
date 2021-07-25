package com.dms.pmsandroid.feature.introduce.ui.activity


import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.databinding.ActivityClubDetailBinding
import com.dms.pmsandroid.feature.introduce.model.ClubDetailModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceClubDetailActivity : BaseActivity<ActivityClubDetailBinding>(R.layout.activity_club__detail) {
    override val vm: IntroduceClubDetailViewModel by viewModel()
    private var clubDetail = ArrayList<ClubDetailModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadClubDetail(accessToken = String(),clubname = String())
    }

    fun bind(position : Int){
        binding.title = clubDetail[position].title
        binding.url = clubDetail[position].url
        binding.explanation = clubDetail[position].explanation
        binding.member = clubDetail[position].member.toString()
        binding.executePendingBindings()
    }

    override fun observeEvent() {
        vm.clubDetail.observe(this,{

        })
        binding.detailBackImg.setOnClickListener(){
            finish()
        }
    }

}