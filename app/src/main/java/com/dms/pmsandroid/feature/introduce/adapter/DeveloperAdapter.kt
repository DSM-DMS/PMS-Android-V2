package com.dms.pmsandroid.feature.introduce.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.ItemDeveloperBinding
import com.dms.pmsandroid.feature.introduce.model.DevelopModel

class DeveloperAdapter(val model: ArrayList<DevelopModel>) :
    RecyclerView.Adapter<DeveloperAdapter.IntroDeveloperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroDeveloperViewHolder {
        val view = ItemDeveloperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroDeveloperViewHolder(view)
    }

    override fun getItemCount() = model.size

    override fun onBindViewHolder(p0: IntroDeveloperViewHolder, p1: Int) = p0.bind(model[p1], p1)

    class IntroDeveloperViewHolder(itemView: ItemDeveloperBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val name = itemView.devNameTv
        val part = itemView.devPartTv
        val image = itemView.devImg
        fun bind(model: DevelopModel, position: Int) {
            name.text = model.developName
            part.text = model.developPart
            when (position) {
                0 -> {
                    Glide.with(image)
                        .load("http://211.38.86.92/media/pms/static/jaewonkim1468.png")
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(image)
                }
                1 -> {
                    Glide.with(image)
                        .load(
                            "http://211.38.86.92/media/pms/static/dlswer23.png"
                        )
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(image)
                }
                2 -> {
                    Glide.with(image)
                        .load("http://211.38.86.92/media/pms/static/Goeun1001.png")
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(image)

                }
                3 -> {
                    Glide.with(image)
                        .load("http://211.38.86.92/media/pms/static/silverbeen.png")
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(image)

                }
                4 -> {
                    Glide.with(image)
                        .load("http://211.38.86.92/media/pms/static/wlsdn1101.png")
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(image)

                }
                5 -> {
                    Glide.with(image)
                        .load("http://211.38.86.92/media/pms/static/smoothbear.png")
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(image)

                }
                6 -> {
                    Glide.with(image)
                        .load("http://211.38.86.92/media/pms/static/jeongjiwoo0522.png")
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(image)
                }
            }

        }
    }
}