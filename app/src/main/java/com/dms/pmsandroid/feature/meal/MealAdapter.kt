package com.dms.pmsandroid.feature.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemMealBinding
import com.dms.pmsandroid.feature.meal.model.MealResponse
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel

class MealAdapter(private val viewModel: MealViewModel) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    private val meals = listOf<MealResponse>(
        MealResponse(null, null, null),
        MealResponse(null, null, null),
        MealResponse(null, null, null)
    )

    inner class MealViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: MealResponse,position: Int) {
            when(position){
                0->binding.mealTimeTv.text = "아침"
                1->binding.mealTimeTv.text = "점심"
                2->binding.mealTimeTv.text = "저녁"
            }
            binding.vm = viewModel
            binding.meal = meal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding =
            ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) =
        holder.bind(meals[position],position)

    override fun getItemCount(): Int = 3

    fun setItems() {

    }
}