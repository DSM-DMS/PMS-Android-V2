package com.dms.pmsandroid.di.module

import com.dms.pmsandroid.data.remote.meal.ProvideMealApi
import com.dms.pmsandroid.feature.meal.viewmodel.MealViewModel
import org.koin.dsl.module

val mealModule = module {
    single { ProvideMealApi(get()) }
    single { MealViewModel(get(),get()) }
}