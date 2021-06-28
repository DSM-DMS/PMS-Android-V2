package com.dms.pmsandroid.feature.introduce.adapter

import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dms.pmsandroid.R
import com.dms.pmsandroid.feature.introduce.model.DevelopModel
import com.google.gson.internal.bind.ArrayTypeAdapter
import org.w3c.dom.Text

class DeveloperAdapter(val model: ArrayList<DevelopModel>) :
        RecyclerView.Adapter<DeveloperAdapter.IntroDeveloperViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): IntroDeveloperViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_developer, p0, false)
        return IntroDeveloperViewHolder(view)
    }

    override fun getItemCount() = model.size

    override fun onBindViewHolder(p0: IntroDeveloperViewHolder, p1: Int) = p0.bind(model[p1], p1)

    class IntroDeveloperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.dev_name_tv)
        val part = itemView.findViewById<TextView>(R.id.dev_part_tv)
        val image = itemView.findViewById<ImageView>(R.id.dev_img)
        fun bind(model: DevelopModel, position: Int) {
            val base64Name = Base64.encodeToString(model.developName.toByteArray(), 0)
                    .replace("\n", "").replace("+", "%2B")
            name.text = model.developName
            part.text = model.developPart
            when (position) {
                0 -> {
                    Glide.with(image)
                            .load("http://211.38.86.92/media/pms/static/jaewonkim1468.png")
                            .into(image)
                }
                1 -> {
                    Glide.with(image)
                            .load(
                                    "http://211.38.86.92/media/pms/static/dlswer23.png"
                            )
                            .into(image)
                }
                2 -> {
                    Glide.with(image)
                            .load("http://211.38.86.92/media/pms/static/Goeun1001.png")
                            .into(image)

                }
                3 -> {
                    Glide.with(image)
                            .load("http://211.38.86.92/media/pms/static/silverbeen.png")
                            .into(image)

                }
                4 -> {
                    Glide.with(image)
                            .load("http://211.38.86.92/media/pms/static/wlsdn1101.png")
                            .into(image)

                }
                5 -> {
                    Glide.with(image)
                            .load("http://211.38.86.92/media/pms/static/smoothbear.png")
                            .into(image)

                }
                6 -> {
                    Glide.with(image)
                            .load("http://211.38.86.92/media/pms/static/jeongjiwoo0522.png")
                            .into(image)

                }
            }

        }
    }
}