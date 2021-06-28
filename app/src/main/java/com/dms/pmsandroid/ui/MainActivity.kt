package com.dms.pmsandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.databinding.ActivityMainBinding
import com.dms.pmsandroid.feature.calendar.ui.CalendarFragment
import com.dms.pmsandroid.feature.introduce.ui.activity.IntroClubActivity
import com.dms.pmsandroid.feature.introduce.ui.activity.IntroduceCompanyActivity
import com.dms.pmsandroid.feature.introduce.ui.activity.IntroduceDeveloperActivity
import com.dms.pmsandroid.feature.introduce.ui.fragment.IntroduceFragment
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import com.dms.pmsandroid.feature.introduce.viewmodel.MainIntroViewModel
import com.dms.pmsandroid.feature.login.ui.activity.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val vm: MainViewModel by viewModel()
    private val introvm : MainIntroViewModel by viewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        vm.checkLogin()
        observeNeedLogin()
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener)
        setFragment()
        observerdevIntent()
        observerworkIntent()
        observerclubIntent()

    }

    override fun onResume() {
        super.onResume()
        vm.checkLogin()
    }

    private fun setFragment(){
        initFragment()
        observeFragment()
    }

    private fun observeNeedLogin() {
        vm.needToLogin.observe(this, Observer {
            if (it) {
                startLogin()
                vm.needToLogin.value = false
            }
        })
    }

    private fun startLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }

    private fun observerdevIntent(){
        introvm.devintroduceClick.observe(this, Observer {
            if(it){
                introIntent()
                introvm.devintroduceClick.value = false
            }
        })
    }

    private fun observerclubIntent(){

    }



    private fun observerworkIntent() {
        introvm.workIntroduceClick.observe(this, Observer {
            if (it)
                workintroIntent()
            introvm.workIntroduceClick.value = false
        })
    }

    private fun introIntent(){
        val devintent = Intent(this,IntroduceDeveloperActivity::class.java)
        startActivity(devintent)
    }

    private fun workintroIntent(){
        val workintent = Intent(this,IntroduceCompanyActivity::class.java)
        startActivity(workintent)
    }

    private fun clubIntent(){
        val clubintent = Intent(this,IntroClubActivity::class.java)
        startActivity(clubintent)
    }



    private val itemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            vm.tabSelectedItem.value = item.itemId
            true
        }

    private val calendarFragment = CalendarFragment()
    private val introduceFragment = IntroduceFragment()
    private var activeFragment: Fragment = calendarFragment

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, calendarFragment)
            .hide(calendarFragment).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, introduceFragment)
            .hide(introduceFragment).commit()
    }

    private fun observeFragment() {
        vm.tabSelectedItem.observe(this, Observer { id ->
            when (id) {
                R.id.menu_calendar_it -> {
                    changeFragment(calendarFragment)
                }
                R.id.menu_info_it -> {
                    changeFragment(introduceFragment)
                }
                R.id.menu_meal_it -> {

                }
                R.id.menu_mypage_it -> {

                }
                R.id.menu_notify_it -> {

                }
            }
        })
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }



}