package com.dms.pmsandroid.presentation.feature.notify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemHomeNoticeBinding
import com.dms.pmsandroid.presentation.feature.notify.model.NoticeListModel
import com.dms.pmsandroid.presentation.feature.notify.viewmodel.NotifyViewModel

class HomeAdapter(private val viewModel: NotifyViewModel) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var homeList = ArrayList<NoticeListModel>()

    inner class HomeViewHolder(private val binding: ItemHomeNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.title = homeList[position].title
            binding.noticehDateTv.text = homeList[position].uploadDate
            binding.id = homeList[position].id
            binding.vm = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ItemHomeNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = homeList.size

    fun setItems(homeList: List<NoticeListModel>) {
        this.homeList = homeList as ArrayList<NoticeListModel>
        notifyDataSetChanged()
    }
}