package com.dms.pmsandroid.feature.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemPointBinding
import com.dms.pmsandroid.feature.mypage.model.PointResponse
import com.dms.pmsandroid.feature.mypage.viewmodel.PointContentViewModel

class PointAdapter(private val viewModel: PointContentViewModel) :
    RecyclerView.Adapter<PointAdapter.PointViewHolder>() {
    private var pointList = ArrayList<PointResponse>()

    inner class PointViewHolder(private val binding: ItemPointBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.vm = viewModel
            binding.reason = pointList[position].reason
            binding.date = pointList[position].date
            binding.point = pointList[position].point.toString()
            binding.executePendingBindings()
            binding.notifyChange()
        }
    }

    override fun getItemCount(): Int {
        return pointList.size
    }

    fun setItem(clubs: List<PointResponse>) {
        this.pointList = clubs as ArrayList<PointResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PointAdapter.PointViewHolder {
        val binding = ItemPointBinding.inflate(LayoutInflater.from(parent.context))
        return PointViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PointAdapter.PointViewHolder, position: Int) {
        holder.bind(position)
    }
}
