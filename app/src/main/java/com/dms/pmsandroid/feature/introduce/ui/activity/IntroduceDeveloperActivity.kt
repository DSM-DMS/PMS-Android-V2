package com.dms.pmsandroid.feature.introduce.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.feature.introduce.adapter.DeveloperAdapter
//import com.dms.pmsandroid.feature.introduce.bindingadapter.DeveloperBindingAdapter
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import com.dms.pmsandroid.ui.MainViewModel

class IntroduceDeveloperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intro_developer_rc = findViewById<RecyclerView>(R.id.intro_developer_rc)



        val developers = arrayListOf(
            DevelopModel("김재원", "Android"),
            DevelopModel("이은별", "Android"),
            DevelopModel("정고은", "IOS"),
            DevelopModel("강은빈", "FrontEnd"),
            DevelopModel("이진우", "FrontEnd"),
            DevelopModel("김정빈", "Server"),
            DevelopModel("정지우", "Server")
        )
        intro_developer_rc.adapter = DeveloperAdapter(developers)
        intro_developer_rc.adapter!!.notifyDataSetChanged()

    }
}







