package com.dms.pmsandroid.feature.notify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemGalleryBinding
import com.dms.pmsandroid.feature.notify.model.GalleryListContent

class GalleryAdapter:RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var galleryList = ArrayList<GalleryListContent>()

    inner class GalleryViewHolder(private val binding:ItemGalleryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position:Int){
            with(binding){
                date = galleryList[position].uploadDate
                title = galleryList[position].title
                photo = galleryList[position].thumbnail
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = galleryList.size

    fun setItems(galleryList:List<GalleryListContent>){
        this.galleryList = galleryList as ArrayList<GalleryListContent>
        notifyDataSetChanged()
    }
}