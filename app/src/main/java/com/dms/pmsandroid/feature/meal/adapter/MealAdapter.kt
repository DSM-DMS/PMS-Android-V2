package com.dms.pmsandroid.feature.meal.adapter

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.R
import com.dms.pmsandroid.databinding.ItemMealBinding
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import com.dms.pmsandroid.feature.meal.model.MealResponse
import kotlin.collections.ArrayList

class MealAdapter(private val viewModel: MealViewModel,context:Context) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    private var meals = MealResponse(null, null, null)

    private val blue = context.resources.getColor(R.color.blue)
    private val green = context.resources.getColor(R.color.green)
    private val red = context.resources.getColor(R.color.red)

    inner class MealViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: ArrayList<String>?, position: Int) {
            when (position) {
                0 -> {
                    binding.mealTimeTv.text = "아침"
                    binding.picture = viewModel.mealPicture.value?.breakfast ?: ""
                    binding.mealTimeFl.setBackgroundColor(blue)
                }
                1 -> {
                    binding.mealTimeTv.text = "점심"
                    binding.picture = viewModel.mealPicture.value?.lunch ?: ""
                    binding.mealTimeFl.setBackgroundColor(green)
                }
                2 -> {
                    binding.mealTimeTv.text = "저녁"
                    binding.picture = viewModel.mealPicture.value?.dinner ?: ""
                    binding.mealTimeFl.setBackgroundColor(red)
                }
            }
            binding.vm = viewModel
            binding.executePendingBindings()
            if (meal != null) {
                var mealList = ""
                for (m in meal) {
                    mealList += m + "\n"
                }
                if (mealList.isBlank()) {
                    binding.meal = "급식이 없습니다"
                } else {
                    binding.meal = mealList
                }
            } else {
                binding.meal = "급식이 없습니다"
            }


            binding.mealContainer.setOnClickListener {
                val animator: ObjectAnimator = (AnimatorInflater.loadAnimator(it.context,R.animator.anim_flip_180) as ObjectAnimator).apply {
                    target = binding.mealItem
                    duration = 500
                }
                animator.start()
                viewModel.showPicture()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding =
            ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        when (position%3) {
            0 -> holder.bind(meals.breakfast, 0)
            1 -> holder.bind(meals.lunch, 1)
            2 -> holder.bind(meals.dinner, 2)
        }
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    fun setItems(meals: MealResponse) {
        this.meals = meals
        notifyDataSetChanged()
    }
}