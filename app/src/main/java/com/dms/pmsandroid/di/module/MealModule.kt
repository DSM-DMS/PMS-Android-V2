package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.meal.MealApiImpl
import com.dms.pmsandroid.feature.meal.MealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mealModule = module {
    single { MealApiImpl() }
    viewModel { MealViewModel(get(),get()) }
}