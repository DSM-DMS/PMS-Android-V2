package com.dms.pmsandroid.feature.introduce.ui.activity


import android.content.Intent
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
import kotlin.math.max

class IntroduceClubDetailActivity :
    BaseActivity<ActivityClubDetailBinding>(R.layout.activity_club_detail) {
    override val vm: IntroduceClubDetailViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clubname = intent.getStringExtra("clubname")
        if (clubname != null) {
            vm.loadClubDetail(clubname)
        }
    }

    override fun observeEvent() {
        vm.clubDetail.observe(this, {
            var getMember = ""
            if (it.member != null) {
                val size = it.member.size - 1
                getMember += "부장 - ${it.member[0]}\n" + "\n" + "부원 - "
                for (posistion in 1..size-1) {
                    getMember += it.member[posistion] + ", "
                }
                getMember += it.member.last()
            }
            binding.run {
                member = getMember
                title = it.title
                url = it.url
                explanation = it.explanation
            }
        })
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}