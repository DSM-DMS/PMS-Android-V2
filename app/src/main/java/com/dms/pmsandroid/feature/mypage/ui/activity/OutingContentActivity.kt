package com.dms.pmsandroid.feature.mypage.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityOutingContentBinding
import com.dms.pmsandroid.feature.introduce.ui.HorizontalItemDecorator
import com.dms.pmsandroid.feature.introduce.ui.VerticalItemDecorator
import com.dms.pmsandroid.feature.mypage.adapter.OutingAdapter
import com.dms.pmsandroid.feature.mypage.viewmodel.OutingContentViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel


class OutingContentActivity :
    BaseActivity<ActivityOutingContentBinding>(R.layout.activity_outing_content) {

    override val vm: OutingContentViewModel by viewModel()
    private val outingAdapter by lazy { OutingAdapter(vm, this) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val number = intent.getIntExtra("number", 0)
        if (number != 0) {
            vm.loadOuting(number)
        }
        binding.outingRc.adapter = outingAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.outingRc.layoutManager = layoutManager
        binding.backImg.setOnClickListener {
            finish()
        }

    }

    override fun observeEvent() {
        vm.run {
            outings.observe(this@OutingContentActivity, {
                outingAdapter.setItem(it.outings)
            })
        }
    }

}