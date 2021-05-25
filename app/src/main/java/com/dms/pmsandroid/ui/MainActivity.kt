package com.dms.pmsandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.data.local.SharedPreferenceStorage
import com.dms.pmsandroid.databinding.ActivityMainBinding
import com.dms.pmsandroid.feature.login.ui.activity.LoginActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val vm by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel() as T
            }
        })[MainViewModel::class.java]
    }

    private val sharedPreferences by lazy {
        SharedPreferenceStorage(this)
    }

    lateinit var mainActivityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityMainBinding.root)

    }

    private fun startLogin(){
        val loginIntent = Intent(this,LoginActivity::class.java)
        startActivity(loginIntent)
    }
}