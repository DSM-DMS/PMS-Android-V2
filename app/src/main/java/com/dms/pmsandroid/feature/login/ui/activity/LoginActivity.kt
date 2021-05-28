package com.dms.pmsandroid.feature.login.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityLoginBinding
import com.dms.pmsandroid.feature.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val vm :LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        observeInput()
    }

    private fun observeInput(){
        vm.userEmail.observe(this, Observer {
            vm.emailDone.value = !it.isNullOrBlank()
        })
        vm.userPassword.observe(this, Observer{
            vm.passwordDone.value = !it.isNullOrBlank()
        })
        vm.doneInput.value = vm.emailDone.value!!&&vm.passwordDone.value!!
    }

    private fun startRegister(){

    }
}