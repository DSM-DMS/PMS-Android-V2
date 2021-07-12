package com.dms.pmsandroid.feature.introduce.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceClubBinding
import com.dms.pmsandroid.feature.introduce.adapter.ClubAdapter
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceClubViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroClubActivity :
    BaseActivity<ActivityIntroduceClubBinding>(R.layout.activity_introduce_club) {

    override val vm: IntroduceClubViewModel by viewModel()
    private val clubAdapter = ClubAdapter(vm)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadClubs()
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.IntroClubRc.layoutManager = gridLayoutManager

    }

    override fun observeEvent() {
        vm.clubs.observe(this, {
            clubAdapter.setItem(it)
        })
    }


}