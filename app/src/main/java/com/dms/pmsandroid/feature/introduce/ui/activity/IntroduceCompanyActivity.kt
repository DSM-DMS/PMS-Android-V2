package com.dms.pmsandroid.feature.introduce.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityIntroduceCompanyBinding
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceCompanyViewModel
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import com.dms.pmsandroid.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroduceCompanyActivity : BaseActivity<ActivityIntroduceCompanyBinding>(R.layout.activity_introduce_company) {

    override val vm: IntroduceCompanyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()
        backPressd()
    }

    override fun observeEvent() {
        TODO("Not yet implemented")
    }

    private fun backPressd(){
        if(vm.backPressd.value == true){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}