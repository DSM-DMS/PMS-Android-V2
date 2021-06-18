package com.dms.pmsandroid.feature.meal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.dms.pmsandroid.R
import com.dms.pmsandroid.base.BaseFragment
import com.dms.pmsandroid.databinding.FragmentLoginBinding
import com.dms.pmsandroid.databinding.FragmentMealBinding
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {

    private val vm : MealViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getMeal("20210618")
    }
}