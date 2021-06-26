package com.dms.pmsandroid.feature.meal.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentMealBinding
import com.dms.pmsandroid.feature.meal.MealAdapter
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {

    private val vm : MealViewModel by viewModel()

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        binding.mealViewVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.mealViewVp.adapter = MealAdapter(vm)
        setCurrentTime()
        vm.getMeal()
    }



    private fun setCurrentTime(){
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(currentTime)
        vm.date.value = dateFormat
    }
}