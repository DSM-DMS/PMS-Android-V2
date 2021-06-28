package com.dms.pmsandroid.feature.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.pmsandroid.databinding.ItemMealBinding
import com.dms.pmsandroid.feature.meal.model.MealResponse

class MealAdapter(private val viewModel: MealViewModel) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    private var meals = MealResponse(null, null, null)

    inner class MealViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: ArrayList<String>?, position: Int) {
            when (position) {
                0 -> {
                    binding.mealTimeTv.text = "아침"
                    binding.picture = viewModel.mealPicture.value?.breakfast?:""
                }
                1 -> {
                    binding.mealTimeTv.text = "점심"
                    binding.picture = viewModel.mealPicture.value?.lunch?:""
                }
                2 -> {
                    binding.mealTimeTv.text = "저녁"
                    binding.picture = viewModel.mealPicture.value?.dinner?:""
                }
            }
            binding.vm = viewModel
            binding.executePendingBindings()
            if (meal != null) {
                var mealList = ""
                for(m in meal){
                    mealList+=m+"\n"
                }
                if(mealList.isBlank()){
                    binding.meal = "급식이 없습니다"
                }else{
                    binding.meal = mealList
                }
            }else{
                binding.meal = "급식이 없습니다"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding =
            ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        when (position) {
            0 -> holder.bind(meals.breakfast, 0)
            1 -> holder.bind(meals.lunch, 1)
            2 -> holder.bind(meals.dinner, 2)
        }
    }

    override fun getItemCount(): Int = 3

    fun setItems(meals:MealResponse) {
        this.meals = meals
        notifyDataSetChanged()
    }
}