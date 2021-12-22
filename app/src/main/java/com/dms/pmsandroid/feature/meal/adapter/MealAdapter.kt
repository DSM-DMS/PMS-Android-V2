package com.dms.pmsandroid.feature.meal.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.ItemMealBinding
import com.dms.pmsandroid.feature.meal.entity.MealItem
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import kotlin.collections.HashMap

class MealAdapter(
    private val viewModel: MealViewModel,
    context: Context
) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    private var meals = HashMap<Int, MealItem>()

    private val blue = context.resources.getColor(R.color.blue)
    private val green = context.resources.getColor(R.color.green)
    private val red = context.resources.getColor(R.color.red)

    inner class MealViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.position = position
            when (position % 3) {
                0 -> {
                    binding.run {
                        mealTimeTv.text = "아침"
                        mealTimeFl.setBackgroundColor(blue)
                    }

                }
                1 -> {
                    binding.run {
                        mealTimeTv.text = "점심"
                        mealTimeFl.setBackgroundColor(green)
                    }
                }
                2 -> {
                    binding.run {
                        mealTimeTv.text = "저녁"
                        mealTimeFl.setBackgroundColor(red)
                    }
                }
            }
            binding.run {
                vm = viewModel
                meal = if (meals.containsKey(position)) meals[position]!!.meals else "급식을 불러오는중 입니다..."
                picture = meals[position]?.picture
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealAdapter.MealViewHolder {
        val binding =
            ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    fun setMeal(mealList: HashMap<Int, MealItem>) {
        meals.putAll(mealList)
        notifyDataSetChanged()
    }

    fun updateMeal(startPosition: Int, mealList: HashMap<Int, MealItem>) {
        meals.putAll(mealList)
        notifyItemRangeChanged(startPosition, 3)
    }
}