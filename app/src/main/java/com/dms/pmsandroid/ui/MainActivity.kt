package com.dms.pmsandroid.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseActivity
import com.dms.pmsandroid.base.EventObserver
import com.dms.pmsandroid.databinding.ActivityMainBinding
import com.dms.pmsandroid.feature.calendar.ui.CalendarFragment
import com.dms.pmsandroid.feature.introduce.ui.activity.IntroduceClubActivity
import com.dms.pmsandroid.feature.introduce.ui.activity.IntroduceDeveloperActivity
import com.dms.pmsandroid.feature.introduce.ui.fragment.IntroduceFragment
import com.dms.pmsandroid.feature.login.ui.activity.LoginActivity
import com.dms.pmsandroid.feature.meal.fragment.MealFragment
import com.dms.pmsandroid.feature.mypage.ui.activity.ChangePasswordActivity
import com.dms.pmsandroid.feature.mypage.ui.activity.PointContentActivity
import com.dms.pmsandroid.feature.notify.ui.activity.GalleryDetailActivity
import com.dms.pmsandroid.feature.notify.ui.activity.NoticeDetailActivity
import com.dms.pmsandroid.feature.notify.ui.fragment.NotifyFragment
import com.dms.pmsandroid.feature.mypage.ui.fragment.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PmsAndroid)
        super.onCreate(savedInstanceState)
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener)
    }

    override fun onStart() {
        super.onStart()
        initFragment()
    }

    override fun onResume() {
        super.onResume()
        vm.checkLogin(isNetworkConnected())
    }

    private fun isNetworkConnected():Boolean{
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun startLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(loginIntent)
    }

    fun startOuting(number: Int){
        val outingIntent = Intent(this,OutingContentActivity::class.java)
        outingIntent.putExtra("number",number)
        startActivity(outingIntent)
    }

    fun startPoint(number: Int){
        val pointIntent = Intent(this,PointContentActivity::class.java)
        pointIntent.putExtra("number",number)
        startActivity(pointIntent)
    }


    fun startChangePassword(){
        val changePWIntent = Intent(this,ChangePasswordActivity::class.java)
        startActivity(changePWIntent)
    }

    fun startDeveloper() {
        val devIntent = Intent(this, IntroduceDeveloperActivity::class.java)
        devIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(devIntent)
    }

    fun startCompany() {
        Toast.makeText(this,"아직 준비중 입니다",Toast.LENGTH_SHORT).show()
        //todo 취업처 api 나오면 startActivity로 넘어가기
        //val workintent = Intent(this, IntroduceCompanyActivity::class.java)
        //startActivity(workintent)
    }

    fun startClub() {
        val clubIntent = Intent(this, IntroduceClubActivity::class.java)
        clubIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(clubIntent)
    }

    fun startGalleryDetail(id: Int) {
        val galleryIntent = Intent(this, GalleryDetailActivity::class.java)
        galleryIntent.run {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra("id", id)
        }
        startActivity(galleryIntent)
    }

    fun startNoticeDetail(id: Int, title: String) {
        val noticeIntent = Intent(this, NoticeDetailActivity::class.java)
        noticeIntent.run{
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra("id", id)
            putExtra("title", title)
        }
        startActivity(noticeIntent)
    }

    fun startDownloadFileViewer(){
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.tf.thinkdroid.viewer")))
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.tf.thinkdroid.viewer")))
        }
    }

    private val itemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            vm.tabSelectedItem.value = item.itemId
            true
        }
    private val calendarFragment = CalendarFragment()
    private val introduceFragment = IntroduceFragment()
    private val mealFragment = MealFragment()
    private val notifyFragment = NotifyFragment()
    private val mypageFragment = MyPageFragment()

    private fun initFragment() {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_container, calendarFragment)
            add(R.id.main_container, introduceFragment)
            add(R.id.main_container, mealFragment)
            add(R.id.main_container, notifyFragment)
            add(R.id.main_container, mypageFragment)
        }.commit()
        resetFragment()
    }

    private fun resetFragment() {
        supportFragmentManager.beginTransaction().run {
            hide(calendarFragment)
            hide(introduceFragment)
            hide(mealFragment)
            hide(notifyFragment)
            hide(mypageFragment)
        }.commit()
        changeFragment(vm.activeFragment?:calendarFragment)
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(vm.activeFragment?:calendarFragment).show(fragment).commit()
        vm.activeFragment = fragment
    }

    override fun observeEvent() {
        vm.run {
            tabSelectedItem.observe(this@MainActivity, { id ->
                when (id) {
                    R.id.menu_calendar_it -> {
                        changeFragment(calendarFragment)
                    }
                    R.id.menu_info_it -> {
                        changeFragment(introduceFragment)
                    }
                    R.id.menu_meal_it -> {
                        changeFragment(mealFragment)
                    }
                    R.id.menu_mypage_it -> {
                        changeFragment(mypageFragment)
                    }
                    R.id.menu_notify_it -> {
                        changeFragment(notifyFragment)
                    }
                }
            })
            needToLogin.observe(this@MainActivity, EventObserver{
                if (it) {
                    startLogin()
                }
            })
        }
    }

    override fun onStop() {
        supportFragmentManager.beginTransaction().run {
            remove(calendarFragment)
            remove(introduceFragment)
            remove(mealFragment)
            remove(notifyFragment)
            remove(mypageFragment)
        }.commit()
        super.onStop()
    }


}