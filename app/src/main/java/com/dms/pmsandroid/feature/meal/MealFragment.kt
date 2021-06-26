package com.dms.pmsandroid.feature.meal

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentMealBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {

    private val vm : MealViewModel by viewModel()

    private val adapter by lazy {
        MealAdapter(vm)
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        binding.mealViewVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.mealViewVp.adapter = adapter
        setCurrentTime()
        vm.getMeal()
        changeTime()
        observeMeals()
    }

    private fun setCurrentTime(){
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(currentTime)
        vm.date.value = dateFormat
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun changeTime(){
        binding.mealNextBtn.setOnClickListener {
            calculateTime(true)
        }
        binding.mealBeforeBtn.setOnClickListener {
            calculateTime(false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateTime(isPlus:Boolean){
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd",Locale.KOREA)
        val calculateDate = LocalDate.parse(vm.date.value,formatter)
        if(isPlus){
            val plusDate = calculateDate.plus(Period.ofDays(1))
            vm.date.value = plusDate.format(DateTimeFormatter.ofPattern("yyyyMMdd",Locale.KOREA))
        }else{
            val minusDate = calculateDate.minus(Period.ofDays(1))
            vm.date.value = minusDate.format(DateTimeFormatter.ofPattern("yyyyMMdd",Locale.KOREA))
        }
        vm.getMeal()
    }

    private fun observeMeals(){
        vm.meals.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            adapter.setItems(it)
        })
    }
}