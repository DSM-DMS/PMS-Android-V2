package com.dms.pmsandroid.feature.notify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemNoticeBinding
import com.dms.pmsandroid.feature.notify.model.NoticeListModel
import com.dms.pmsandroid.feature.notify.viewmodel.NotifyViewModel

class NoticeAdapter(private val viewModel:NotifyViewModel): RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    private var notices = ArrayList<NoticeListModel>()

    inner class NoticeViewHolder(private val binding:ItemNoticeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.noticeTitleTv.text = notices[position].title
            binding.noticeDateTv.text = notices[position].uploadDate
            binding.id = notices[position].id
            binding.vm = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = notices.size

    fun setItems(notices:List<NoticeListModel>){
        this.notices = notices as ArrayList<NoticeListModel>
        notifyDataSetChanged()
    }
}