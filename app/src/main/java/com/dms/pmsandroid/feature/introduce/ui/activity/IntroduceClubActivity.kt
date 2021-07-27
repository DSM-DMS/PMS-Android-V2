package com.dms.pmsandroid.feature.introduce.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceClubBinding
import com.dms.pmsandroid.feature.introduce.adapter.ClubAdapter
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubViewModel
import com.dms.pmsandroid.generated.callback.OnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceClubActivity :
    BaseActivity<ActivityIntroduceClubBinding>(R.layout.activity_introduce_club) {

    override val vm: IntroduceClubViewModel by viewModel()
    private val clubAdapter by lazy { ClubAdapter(vm) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadClubs()
        intent()
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.introClubRc.layoutManager = gridLayoutManager
        binding.introClubRc.adapter = clubAdapter
        binding.backImg.setOnClickListener() {
            finish()
        }
    }

    private fun checkClick() {

    }

    override fun observeEvent() {
        vm.clubs.observe(this, {
            clubAdapter.setItem(it.clubs)
        })
    }

    fun intent() {

        val intent = Intent(this, IntroduceClubDetailActivity::class.java)
        startActivity(intent)
    }
}