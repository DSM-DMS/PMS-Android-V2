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
import com.jakewharton.rxbinding4.view.clicks
import org.koin.android.ext.android.inject
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {

    override val vm: MealViewModel by inject()
    private val mainVm: MainViewModel by inject()

    private val mealAdapter by lazy {
        MealAdapter(vm, requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        vm.getMeal()
    }

    private val dateDialog: MealDatePickerDialog by lazy {
        MealDatePickerDialog()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {

        binding.mealViewVp.run {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = mealAdapter
            offscreenPageLimit = 3

            setIndicator()
            setCurrentItem(vm.currentPosition.value!!, false)

            addItemDecoration(object : RecyclerView.ItemDecoration() {
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

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position % 3) {
                        0 -> {
                            if (vm.currentPosition.value!! % 3 == 2) { //이전페이지가 저녁이었을 때
                                vm.run {
                                    nextDay()
                                }

                            }
                        }
                        2 -> {
                            if (vm.currentPosition.value!! % 3 == 0) { //이전페이지가 아침이었을 때
                                vm.run {
                                    beforeDay()
                                }
                            }
                        }
                    }
                    vm.currentPosition.value = position
                }
            })

            val screenWidth = resources.displayMetrics.widthPixels
            val pageMargin = resources.getDimension(R.dimen.pageMargin)
            val pageWidth = screenWidth - (pageMargin * 2) - 50
            val offsetPx = screenWidth - pageWidth - pageMargin

            setPageTransformer { page, position ->
                page.translationX = -offsetPx * position
            }
            
        }

        binding.mealDateCl.clicks().debounce(200, TimeUnit.MILLISECONDS).subscribe {
            dateDialog.show(requireActivity().supportFragmentManager, "MealDatePickerDialog")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setIndicator() {
        val currentTime = LocalDateTime.now()

        when (currentTime.hour) {
            in 10..14 -> {
                vm.currentPosition.value = vm.currentPosition.value!! + 1
            }

            in 15..23 -> {
                vm.currentPosition.value = vm.currentPosition.value!! + 2
            }
        }
        binding.mealViewVp.post {
            binding.mealViewVp.setCurrentItem(
                vm.currentPosition.value!!,
                false
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun observeEvent() {
        vm.run {
            meals.observe(viewLifecycleOwner, {
                mealAdapter.setItems(it)
            })
        }
        mainVm.doneToken.observe(viewLifecycleOwner, {
            if (it) {
                vm.getMeal()
            }
        })
    }
}