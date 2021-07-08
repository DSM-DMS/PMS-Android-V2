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
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import com.dms.pmsandroid.feature.meal.adapter.MealAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {

    override val vm: MealViewModel by inject()

    private val adapter by lazy {
        MealAdapter(vm)
    }


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mealViewVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.mealViewVp.adapter = adapter
        setCurrentTime()
        vm.getMeal()
        changeTime()
        setIndicator()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCurrentTime() {
        val currentTime = LocalDate.now().minus(Period.ofDays(1))
        val dateFormat = currentTime.format(DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA))
        val weekDay = currentTime.dayOfWeek
        vm.run {
            date.value = dateFormat
            weekDate.value = weekDay.value
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun changeTime() {
        binding.mealNextBtn.setOnClickListener {
            calculateTime(true)
        }
        binding.mealBeforeBtn.setOnClickListener {
            calculateTime(false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateTime(isPlus: Boolean) {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA)
        val calculateDate = LocalDate.parse(vm.date.value, formatter)
        if (isPlus) {
            val plusDate = calculateDate.plus(Period.ofDays(1))
            vm.date.value = plusDate.format(DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA))
            vm.weekDate.value = plusDate.dayOfWeek.value
        } else {
            val minusDate = calculateDate.minus(Period.ofDays(1))
            vm.date.value = minusDate.format(DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA))
            vm.weekDate.value = minusDate.dayOfWeek.value
        }
        vm.getMeal()
    }

    private fun updatePageView() {
        val position = binding.mealViewVp.currentItem
        adapter.notifyDataSetChanged()
        binding.mealViewVp.currentItem = position
    }

    private fun setIndicator() {
        TabLayoutMediator(binding.tabMealBanner, binding.mealViewVp) { _, _ ->
            binding.mealViewVp.currentItem = binding.tabMealBanner.selectedTabPosition
        }.attach()
    }

    override fun observeEvent() {
        vm.run {
            showPicture.observe(viewLifecycleOwner, {
                updatePageView()
            })
            mealPicture.observe(viewLifecycleOwner, {
                updatePageView()
            })
            meals.observe(viewLifecycleOwner, {
                adapter.setItems(it)
            })
        }
    }

}