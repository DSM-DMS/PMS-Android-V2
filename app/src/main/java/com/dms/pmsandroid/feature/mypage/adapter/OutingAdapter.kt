package com.dms.pmsandroid.feature.mypage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.ItemOutingBinding
import com.dms.pmsandroid.feature.mypage.model.OutingResponse
import com.dms.pmsandroid.feature.mypage.viewmodel.OutingContentViewModel

class OutingAdapter(private val viewModel: OutingContentViewModel, context: Context) :
    RecyclerView.Adapter<OutingAdapter.OutingViewHolder>() {
    private var outingList = ArrayList<OutingResponse>()

    private val blue = context.resources.getColor(R.color.blue)
    private val red = context.resources.getColor(R.color.red)

    inner class OutingViewHolder(private val binding: ItemOutingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.date = outingList[position].date
            binding.reason = "사유 - " + outingList[position].reason
            binding.place = "장소 - " + outingList[position].place
            if (outingList[position].type == "DISEASE") {
                binding.checkV.setBackgroundColor(red)
            } else
                binding.checkV.setBackgroundColor(blue)
            binding.vm = viewModel
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
        val binding = ItemOutingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OutingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OutingViewHolder, position: Int) {
        holder.bind(position)
    }
}
