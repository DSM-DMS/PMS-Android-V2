package com.dms.pmsandroid.feature.meal.fragment

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentMealBinding
import com.dms.pmsandroid.feature.meal.MealDatePickerDialog
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import com.dms.pmsandroid.feature.meal.adapter.MealAdapter
import com.dms.pmsandroid.ui.MainViewModel
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {

    override val vm: MealViewModel by inject()
    private val mainVm: MainViewModel by inject()

    private val adapter by lazy {
        MealAdapter(vm, requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCurrentTime()
        initView()
        vm.getMeal()
        changeTime()
        setIndicator()
    }

    private var selectedPosition = Int.MAX_VALUE / 2

    private val dateDialog: MealDatePickerDialog by lazy {
        MealDatePickerDialog()
    }


    private fun initView() {

        binding.mealViewVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.mealViewVp.adapter = adapter
        binding.mealViewVp.offscreenPageLimit = 1

        binding.mealViewVp.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.right = 20
                outRect.left = 20
            }
        })
        binding.mealViewVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position % 3) {
                    0 -> {
                        if (selectedPosition < position) {
                            calculateTime(true)
                        }
                    }
                    2 -> {
                        if (selectedPosition > position) {
                            calculateTime(false)
                        }
                    }
                }
                selectedPosition = position
            }
        })

        val screenWidth = resources.displayMetrics.widthPixels
        val pageMargin = resources.getDimension(R.dimen.pageMargin)
        val pageWidth = screenWidth - (pageMargin * 2) - 50
        val offsetPx = screenWidth - pageWidth - pageMargin
        binding.mealViewVp.setPageTransformer { page, position ->
            page.translationX = -offsetPx * position
        }

        binding.mealDateCl.setOnClickListener {
            dateDialog.show(requireActivity().supportFragmentManager, "MealDatePickerDialog")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCurrentTime() {
        val currentTime = LocalDate.now()
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
        binding.mealViewVp.post { binding.mealViewVp.setCurrentItem(Int.MAX_VALUE / 2, false) }

    }

    @RequiresApi(Build.VERSION_CODES.O)
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
        mainVm.doneToken.observe(viewLifecycleOwner, {
            if (it) {
                vm.getMeal()
            }
        })
    }
}