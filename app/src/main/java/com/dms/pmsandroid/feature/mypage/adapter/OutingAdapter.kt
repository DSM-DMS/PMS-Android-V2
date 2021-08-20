package com.dms.pmsandroid.feature.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemOutingBinding
import com.dms.pmsandroid.feature.mypage.model.OutingResponse
import com.dms.pmsandroid.feature.mypage.viewmodel.OutingContentViewModel

class OutingAdapter(private val viewModel: OutingContentViewModel) :
    RecyclerView.Adapter<OutingAdapter.OutingViewHolder>() {
    private var outingList = ArrayList<OutingResponse>()


    inner class OutingViewHolder(private val binding: ItemOutingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position:Int) {
            binding.date = "날짜" + outingList[position].date.toString()
            binding.reason =  "이유" +outingList[position].reason
            binding.place =  "장소" +outingList[position].place
            binding.vm = viewModel
            binding.executePendingBindings()
            binding.notifyChange();
        }
    }

    override fun getItemCount(): Int {
        return outingList.size
    }

    fun setItem(outing: List<OutingResponse>) {
        this.outingList = outing as ArrayList<OutingResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutingViewHolder {
        val binding = ItemOutingBinding.inflate(LayoutInflater.from(parent.context))
        return OutingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OutingViewHolder, position: Int) {
        holder.bind(position)
    }
}
