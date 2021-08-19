package com.dms.pmsandroid.feature.mypage.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityOutingContentBinding
import com.dms.pmsandroid.feature.introduce.ui.HorizontalItemDecorator
import com.dms.pmsandroid.feature.mypage.adapter.OutingAdapter
import com.dms.pmsandroid.feature.mypage.viewmodel.OutingContentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingContentActivity : BaseActivity<ActivityOutingContentBinding>(R.layout.activity_outing_content) {

    override val vm : OutingContentViewModel by viewModel()
    private val outingAdapter by lazy { OutingAdapter(vm) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun observeEvent() {


    }

}