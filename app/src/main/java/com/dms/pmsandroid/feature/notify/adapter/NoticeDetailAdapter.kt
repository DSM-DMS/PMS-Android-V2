package com.dms.pmsandroid.feature.notify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemNoticeDetailHeaderBinding
import com.dms.pmsandroid.feature.notify.viewmodel.NoticeDetailViewModel

class NoticeDetailAdapter(private val viewModel: NoticeDetailViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val _HEADER = 0
    inner class NoticeDetailHeaderViewHolder(private val binding:ItemNoticeDetailHeaderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.vm = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0->{
                val binding = ItemNoticeDetailHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                NoticeDetailHeaderViewHolder(binding)
            }
            else->{
                val binding = ItemNoticeDetailHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                NoticeDetailHeaderViewHolder(binding)//todo
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position==0){
            (holder as NoticeDetailHeaderViewHolder).bind()
        }else{
            (holder as NoticeDetailHeaderViewHolder).bind()//todo
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position==0){
            _HEADER
        }else{
            1
        }
    }

    override fun getItemCount(): Int =1
}