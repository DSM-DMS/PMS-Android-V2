package com.dms.pmsandroid.feature.introduce.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceDeveloperBinding
import com.dms.pmsandroid.feature.introduce.adapter.DeveloperAdapter
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import com.dms.pmsandroid.feature.introduce.ui.HorizontalItemDecorator
import com.dms.pmsandroid.feature.introduce.ui.VerticalItemDecorator
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceDeveloperActivity : BaseActivity<ActivityIntroduceDeveloperBinding>(R.layout.activity_introduce_developer) {

    override val vm: IntroduceDeveloperViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intro_developer_rc = findViewById<RecyclerView>(R.id.intro_developer_rc)

        val developers = arrayListOf(
                DevelopModel("김재원", "Android"),
                DevelopModel("이은별", "Android"),
                DevelopModel("정고은", "iOS/PM"),
                DevelopModel("강은빈", "Web"),
                DevelopModel("이진우", "Web"),
                DevelopModel("김정빈", "Back-End"),
                DevelopModel("정지우", "Back-End")
        )
        intro_developer_rc.adapter = DeveloperAdapter(developers)
        intro_developer_rc.adapter!!.notifyDataSetChanged()
        binding.introDeveloperRc.addItemDecoration(VerticalItemDecorator(30))

    }

    override fun observeEvent() {
        binding.backImg.setOnClickListener(){
            finish()
        }
    }

}







