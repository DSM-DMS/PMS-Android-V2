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
        setIndicator()
    }

    private val dateDialog: MealDatePickerDialog by lazy {
        MealDatePickerDialog()
    }


    private fun initView() {

        binding.mealViewVp.run {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = mealAdapter

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

        binding.mealDateCl.clicks().debounce(200,TimeUnit.MILLISECONDS).subscribe {
            dateDialog.show(requireActivity().supportFragmentManager, "MealDatePickerDialog")
        }
    }

    private fun setIndicator() {
        binding.mealViewVp.post { binding.mealViewVp.setCurrentItem(Int.MAX_VALUE / 2, false) }
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