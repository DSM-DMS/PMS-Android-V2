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
import com.jakewharton.rxbinding4.view.clicks
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {

    override val vm: MealViewModel by inject()

    private val mealAdapter by lazy {
        MealAdapter(vm, requireContext())
    }

    private val dateDialog: MealDatePickerDialog by lazy {
        MealDatePickerDialog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initViewModel()
        setMealViewPager()
        setOpenDatePickerDialog()
    }

    override fun observeEvent() {
        vm.run {
            meals.observe(viewLifecycleOwner, {
                mealAdapter.setMeal(it)
                binding.mealViewVp.setCurrentItem(vm.currentPosition.value!!, true)
            })
            currentPosition.observe(viewLifecycleOwner, {
                binding.mealViewVp.setCurrentItem(it, true)
            })
            showPicture.observe(viewLifecycleOwner, {
                mealAdapter.notifyDataSetChanged()
            })
            needUpdateMealItems.observe(viewLifecycleOwner, {
                mealAdapter.updateMeal(it.startPosition, it.meals)
            })
        }
    }

    private fun initViewModel() {
        vm.run {
            currentPosition.value = Int.MAX_VALUE / 2
            selectedDate.value = LocalDate.now()
            getInitMeal()
        }
    }

    private fun setMealViewPager() {
        initViewPager()
        showSideItemsCorner()
        setViewPagerIndicator()
        setViewPagerSelectedListener()
    }

    private fun initViewPager() {
        binding.mealViewVp.run {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = mealAdapter
            offscreenPageLimit = 3
        }
    }

    private fun setViewPagerIndicator() {
        val screenWidth = resources.displayMetrics.widthPixels
        val pageMargin = resources.getDimension(R.dimen.pageMargin)
        val pageWidth = screenWidth - (pageMargin * 2) - 50
        val offsetPx = screenWidth - pageWidth - pageMargin

        binding.mealViewVp.setPageTransformer { page, position ->
            page.translationX = -offsetPx * position
        }

    }

    private fun setViewPagerSelectedListener() {
        binding.mealViewVp.run {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position % 3) {
                        0 -> {
                            if (positionWasDinner()) {
                                vm.run {
                                    nextDay()
                                }
                            }
                        }
                        2 -> {
                            if (positionWasBreakfast()) {
                                vm.run {
                                    beforeDay()
                                }
                            }
                        }
                    }
                    vm.currentPosition.value = position
                }
            })
            setCurrentItem(Int.MAX_VALUE / 2, false)
        }
    }
    private fun showSideItemsCorner() {
         binding.mealViewVp.addItemDecoration(object : RecyclerView.ItemDecoration() {
             override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                 outRect.right = 20
                 outRect.left = 20
             }
         })
    }
    private fun setOpenDatePickerDialog() {
        binding.mealDateCl.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
            dateDialog.show(requireActivity().supportFragmentManager, "MealDatePickerDialog")
        }
    }

    private fun positionWasDinner() = vm.currentPosition.value!! % 3 == 2

    private fun positionWasBreakfast() = vm.currentPosition.value!! % 3 == 0
}