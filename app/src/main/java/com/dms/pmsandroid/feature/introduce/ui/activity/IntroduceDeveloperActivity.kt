package com.dms.pmsandroid.feature.introduce.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceDeveloperBinding
import com.dms.pmsandroid.feature.introduce.adapter.DeveloperAdapter
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceCompanyViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import com.dms.pmsandroid.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceDeveloperActivity : BaseActivity<ActivityIntroduceDeveloperBinding>(R.layout.activity_introduce_developer) {


    override val vm: IntroduceDeveloperViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intro_developer_rc = findViewById<RecyclerView>(R.id.intro_developer_rc)

        val developers = arrayListOf(
                DevelopModel("김재원", "Android"),
                DevelopModel("이은별", "Android"),
                DevelopModel("정고은", "iOS"),
                DevelopModel("강은빈", "FrontEnd"),
                DevelopModel("이진우", "FrontEnd"),
                DevelopModel("김정빈", "Server"),
                DevelopModel("정지우", "Server")
        )
        intro_developer_rc.adapter = DeveloperAdapter(developers)
        intro_developer_rc.adapter!!.notifyDataSetChanged()

    }

    override fun observeEvent() {
        binding.backImg.setOnClickListener(){
            onBackPressed()
        }
    }

}







