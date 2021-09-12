package com.dms.pmsandroid.feature.mypage.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityPointContentBinding
import com.dms.pmsandroid.feature.mypage.adapter.PointAdapter
import com.dms.pmsandroid.feature.mypage.viewmodel.PointContentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PointContentActivity :
    BaseActivity<ActivityPointContentBinding>(R.layout.activity_point_content) {

    override val vm: PointContentViewModel by viewModel()
    private val pointAdapter by lazy { PointAdapter(vm, binding.pointRc.context) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val number = intent.getIntExtra("number", 0)
        if (number != 0) {
            vm.loadPoint(number)
        }
        binding.pointRc.adapter = pointAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.run {
            pointRc.layoutManager = layoutManager
            backImg.setOnClickListener {
                finish()
            }
            pointShimmerFl.startShimmer()
            binding.pointRc.visibility = View.INVISIBLE
        }
    }

    override fun observeEvent() {
        vm.run {
            points.observe(this@PointContentActivity, {
                pointAdapter.setItem(it.points)
                binding.pointShimmerFl.hideShimmer()
                binding.pointShimmerFl.visibility = View.INVISIBLE
                binding.pointRc.visibility = View.VISIBLE
            })
        }
    }
}