package com.dms.pmsandroid.feature.login.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityLoginBinding
import com.dms.pmsandroid.feature.login.ui.fragment.LoginFragment
import com.dms.pmsandroid.feature.login.ui.fragment.RegisterFragment
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val vm : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        startLogin()
        observeRegister()
    }

    private fun startLogin(){
        supportFragmentManager.beginTransaction().add(R.id.login_container,LoginFragment()).commit()
    }

    private fun observeRegister(){
        vm.needRegister.observe(this, Observer {
            if(it){
                startRegister()
            }
        })
    }

    private fun startRegister(){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.setCustomAnimations(R.anim.silde_in_up,R.anim.slide_out_up)
        fragmentManager.replace(R.id.login_container,RegisterFragment()).commit()
        vm.needRegister.value = false
    }
}