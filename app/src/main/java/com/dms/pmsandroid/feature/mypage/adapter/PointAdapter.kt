package com.dms.pmsandroid.feature.mypage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.ItemPointBinding
import com.dms.pmsandroid.feature.mypage.model.PointResponse
import com.dms.pmsandroid.feature.mypage.viewmodel.PointContentViewModel

class PointAdapter(private val viewModel: PointContentViewModel, context: Context) :
    RecyclerView.Adapter<PointAdapter.PointViewHolder>() {
    private var pointList = ArrayList<PointResponse>()

    private val blue = context.resources.getColor(R.color.blue)
    private val red = context.resources.getColor(R.color.red)

    inner class PointViewHolder(private val binding: ItemPointBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.vm = viewModel
            binding.reason = pointList[position].reason
            binding.date = pointList[position].date
            if (pointList[position].type) {
                binding.point = pointList[position].point.toString()
                binding.pointTv.setBackgroundColor(blue)
            } else
                binding.point = pointList[position].point.toString()
                binding.pointTv.setBackgroundColor(red)
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
