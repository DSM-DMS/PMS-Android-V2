package com.dms.pmsandroid.feature.introduce.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.ActivityMainBinding
import com.dms.pmsandroid.feature.introduce.adapter.DeveloperAdapter
import com.dms.pmsandroid.feature.introduce.bindingadapter.DeveloperBindingAdapter
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import com.dms.pmsandroid.ui.MainViewModel

@Suppress("DEPRECATION")
class IntroduceDeveloperActivity : AppCompatActivity() {

    var data = MutableLiveData<ArrayList<DevelopModel>>()
    lateinit var adapter: DeveloperAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: IntroduceDeveloperViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_introduce_developer)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_introduce_developer)
        viewmodel = ViewModelProviders.of(this).get(IntroduceDeveloperViewModel::class.java)
        

    }

    //뒤로 가기
    override fun onBackPressed() {
        super.onBackPressed()
        setContentView(R.layout.activity_introduce_developer)

    }
}







