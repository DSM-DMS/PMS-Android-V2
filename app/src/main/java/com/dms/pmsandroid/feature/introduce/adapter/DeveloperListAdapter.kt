package com.dms.pmsandroid.feature.introduce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.ItemDeveloperBinding
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import com.dms.pmsandroid.feature.introduce.viewmodel.IntroduceDeveloperViewModel
import java.nio.file.Files.list
import java.util.Collections.list

class DeveloperListAdapter(var data:LiveData,<ArrayList<DevelopModel>>) :
RecyclerView.Adapter<DeveloperListAdapter.MyViewHolder>(){

    inner class MyViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
    ) {
        var tv1 = itemView.firstTV
        var tv2 = itemView.secondTV
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

}