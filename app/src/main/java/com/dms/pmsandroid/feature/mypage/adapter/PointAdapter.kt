package com.dms.pmsandroid.feature.mypage.adapter

import android.graphics.Point
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemPointBinding
import com.dms.pmsandroid.feature.mypage.model.PointListResponse
import com.dms.pmsandroid.feature.mypage.model.PointResponse
import com.dms.pmsandroid.feature.mypage.viewmodel.PointContentViewModel

class PointAdapter(private val viewModel: PointContentViewModel) :
    RecyclerView.Adapter<PointAdapter.PointViewHolder>() {
    private var pointList = ArrayList<PointResponse>()

    inner class PointViewHolder(private val binding: ItemPointBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //여기에 학생 코드를 한번 더 인증 받아야 하나
        fun bind(response: PointResponse) {
            if (response.type == true) {
                binding.vm = viewModel
                binding.reason = response.reason
                binding.date = response.date
                if (response.point > 0){
                    //색상 바꾸기?
                    response.point
                }
                binding.executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int {
        return pointList.size
    }

    fun setItem(points: List<PointListResponse>) {
        this.pointList = points as ArrayList<PointResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val binding = ItemPointBinding.inflate(LayoutInflater.from(parent.context))
        return PointViewHolder(binding)
    }

    override fun onBindViewHolder(p0: PointViewHolder, p1: Int) = p0.bind(pointList[p1])
}