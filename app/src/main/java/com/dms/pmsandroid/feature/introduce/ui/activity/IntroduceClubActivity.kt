package com.dms.pmsandroid.feature.introduce.ui.activity

import android.content.Intent
import android.os.Bundle
import com.dms.pmsandroid.feature.introduce.ui.HorizontalItemDecorator
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceClubBinding
import com.dms.pmsandroid.feature.introduce.adapter.ClubAdapter
import com.dms.pmsandroid.feature.introduce.ui.VerticalItemDecorator
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceClubActivity :
    BaseActivity<ActivityIntroduceClubBinding>(R.layout.activity_introduce_club) {

    override val vm: IntroduceClubViewModel by viewModel()
    private val clubAdapter by lazy { ClubAdapter(vm) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadClubs()
        binding.introClubRc.addItemDecoration(HorizontalItemDecorator(25))
        binding.introClubRc.addItemDecoration(VerticalItemDecorator(20))
        binding.introClubRc.adapter = clubAdapter
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.clubMyCl.setOnClickListener {
            startMyClubDetail()
        }
    }

    override fun observeEvent() {
        vm.run {
            clubs.observe(this@IntroduceClubActivity, {
                clubAdapter.setItem(it.clubs)
            })
            clickedClubId.observe(this@IntroduceClubActivity, {
                startClubDetail(it)
            })
        }
    }

    private fun startMyClubDetail() {
        val clubIntent = Intent(this, IntroduceClubDetailActivity::class.java)
        clubIntent.putExtra("clubname", "DMS")
        startActivity(clubIntent)
    }

    private fun startClubDetail(clubname: String) {
        val clubIntent = Intent(this, IntroduceClubDetailActivity::class.java)
        clubIntent.putExtra("clubname", clubname)
        startActivity(clubIntent)
    }
}