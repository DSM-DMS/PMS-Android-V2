package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mealModule = module {
    viewModel { MealViewModel() }
}