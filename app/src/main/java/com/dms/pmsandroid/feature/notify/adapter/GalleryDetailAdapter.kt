package com.dms.pmsandroid.feature.notify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemGalleryDetailBinding
import com.dms.pmsandroid.databinding.ItemGalleryDetailHeaderBinding
import com.dms.pmsandroid.feature.notify.viewmodel.GalleryDetailViewModel

class GalleryDetailAdapter(private val viewModel:GalleryDetailViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var attach = ArrayList<String>()
    var title = ""
    var body = ""

    private val _HEADER = 0
    private val _BODY = 1

    override fun getItemViewType(position: Int): Int {
        return if(position==0){
            _HEADER
        }else{
            _BODY
        }
    }

    inner class GalleryDetailViewHolder(private val binding: ItemGalleryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.photo = attach[position-1]
        }
    }

    inner class GalleryDetailHeaderViewHolder(private val binding: ItemGalleryDetailHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            binding.vm = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == _HEADER){
            val binding = ItemGalleryDetailHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            GalleryDetailHeaderViewHolder(binding)
        } else{
            val binding = ItemGalleryDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            GalleryDetailViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position==0){
            (holder as GalleryDetailHeaderViewHolder).bind()
        }else{
            (holder as GalleryDetailViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int = attach.size + 1

    fun setGalleryDetail(attach:List<String>,title:String,body:String){
        this.attach = attach as ArrayList<String>
        this.title = title
        this.body = body
        notifyDataSetChanged()
    }

}