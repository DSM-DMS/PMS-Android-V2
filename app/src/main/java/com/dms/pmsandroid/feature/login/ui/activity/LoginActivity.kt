package com.dms.pmsandroid.feature.login.ui.activity

import android.os.Bundle
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
        observeDoneLogin()
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

    private fun observeDoneLogin(){
        vm.doneLogin.observe(this, Observer {
            if(it){
                finish()
            }
        })
    }

    private fun startRegister(){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.setCustomAnimations(R.anim.silde_in_up,R.anim.slide_out_down)
        fragmentManager.replace(R.id.login_container,RegisterFragment()).commit()
        vm.needRegister.value = false
    }

    private var lastTimeBackPressed: Long = -1500

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed <= 1500) {
            moveTaskToBack(true)
            finish()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
        lastTimeBackPressed = System.currentTimeMillis()
        Toast.makeText(this, "뒤로가기 버튼을 한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()

    }
}