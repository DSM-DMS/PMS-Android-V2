package com.dms.pmsandroid.presentation.feature.login.ui.activity

import android.os.Bundle
import com.dms.pmsandroid.R
import com.dms.pmsandroid.presentation.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityLoginBinding
import com.dms.pmsandroid.presentation.feature.login.ui.fragment.FindPasswordFragment
import com.dms.pmsandroid.presentation.feature.login.ui.fragment.LoginFragment
import com.dms.pmsandroid.presentation.feature.login.ui.fragment.RegisterFragment
import com.dms.pmsandroid.presentation.feature.login.viewmodel.LoginViewModel
import com.dms.pmsandroid.presentation.ui.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override val vm: LoginViewModel by viewModel()
    private val mainVm: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        startLogin()

        observeEvent()
    }

    private fun startLogin() {
        supportFragmentManager.beginTransaction().add(R.id.login_container, LoginFragment())
            .commit()
    }

    private fun startRegister() {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.setCustomAnimations(R.anim.silde_in_up, R.anim.slide_out_down)
        fragmentManager.replace(R.id.login_container, RegisterFragment()).commit()
    }

    private fun startFindPassword() {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.run {
            setCustomAnimations(R.anim.silde_in_up, R.anim.slide_out_down)
            replace(R.id.login_container, FindPasswordFragment()).commit()
        }
    }

    override fun observeEvent() {
        vm.run {
            needRegister.observe(this@LoginActivity, {
                startRegister()
            })

            findPassword.observe(this@LoginActivity, {
                startFindPassword()
            })
            doneLogin.observe(this@LoginActivity, {
                if (it) {
                    mainVm.doneToken.value = true
                    finish()
                }
            })
        }
    }


}