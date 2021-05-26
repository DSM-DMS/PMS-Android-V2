package com.dms.pmsandroid.feature.introduce.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import org.w3c.dom.Text

class DeveloperAdapter(var data: LiveData<ArrayList<DevelopModel>>) :
    RecyclerView.Adapter<DeveloperAdapter.MyViewHolder>() {

    inner class MyViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.developer_item, parent, false)
    ) {

        var name = itemView.findViewById<TextView>(R.id.intro_developer_tv)
        var part = itemView.findViewById<TextView>(R.id.intro_part_tv)
        var photo = itemView.findViewById<ImageView>(R.id.intro_developer_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun getItemCount(): Int {
        Log.e("datasize", "" + data.value!!.size)
        return data.value!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        data.value!!.get(position).let { item ->
            with(holder) {
                name.text = item.developName
                part.text = item.developPart
                photo.setImageResource(R.id.intro_developer_img)

            }
        }
    }
}